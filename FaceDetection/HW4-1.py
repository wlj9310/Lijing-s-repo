Npos=2000
Nneg=2000



def getWeakClassifier(features, weight, label, Npos):
	Nfeatures, Nimgs = features.shape
	currentMin = np.inf
	tPos = np.matlib.repmat(np.sum(weight[:Npos,0]), Nimgs,1) 
	tNeg = np.matlib.repmat(np.sum(weight[Npos:Nimgs,0]), Nimgs,1)
	
	for i in xrange(Nfeatures):
		oneFeature = features[i,:]
		sortedFeature = np.sort(oneFeature)
		sortedIdx = np.argsort(oneFeature)
		sortedWeight = weight[sortedIdx]
		sortedLabel = label[sortedIdx]
		sPos = cumsum(np.multiply(sortedWeight,sortedLabel)) 
		sNeg = cumsum(sortedWeight)- sPos
		
		sPos = sPos.reshape(sPos.shape[0],1)
		sNeg = sNeg.reshape(sNeg.shape[0],1)
		errPos = sPos + (tNeg -sNeg)
		errNeg = sNeg + (tPos -sPos)
	
		allErrMin = np.minimum(errPos, errNeg) # pointwise min
		
		errMin = np.min(allErrMin)
		idxMin = np.argmin(allErrMin)
		
		result = np.zeros((Nimgs,1))
		if (errPos [idxMin] <= errNeg[idxMin]):
			p = 1
			end = result.shape[0]
			result[idxMin+1:end] = 1
			result[sortedIdx] = np.copy(result)
			
		else:
			p = -1
			result[:idxMin+1] = 1
			result[sortedIdx] = np.copy(result)
		
		if (errMin < currentMin):
			print errMin, i
			currentMin = errMin
			if (idxMin==0):
				theta = sortedFeature[0] - 0.5
			elif (idxMin==Nfeatures-1):
				theta = sortedFeature[Nfeatures-1] + 0.5
			else:
				theta = (sortedFeature[idxMin]+sortedFeature[idxMin - 1])/2
			polarity = p
			featureIdx = i
			bestResult = result
	return currentMin, theta, polarity, featureIdx, bestResult


features=getHaar(row, col, Npos, Nneg)
feature1=np.copy(features)




Nimg=features.shape[1]
iniweight = np.zeros((Nimg,1))
iniweight[:,0] = 1/Nimg


#label face as 1, nonface as 0.
label = np.zeros((4000,1))
label[:2000] = 1

def reweight(weight, currentMin,result,label):
	alphat=np.log((1-currentMin)/currentMin)/2
	z=2*np.sqrt((1-currentMin)*currentMin)
	weighta=np.copy(weight)
	labela=np.copy(label)
	resulta=np.copy(result)
	# result: face=1 nonface=0
	for k in xrange(0,len(weight)):
		if resulta[k]==0:
			resulta[k]=-1
		if labela[k]==0:
			labela[k]=-1
	# 判对 y*h=1 判错y*h=-1
	yh=labela*result
	for j in xrange(0,len(weight)): 
		weighta[j]=weight[j]*np.exp(-alphat*yh[j])/z
	return weighta
np.savetxt('features.txt', features)



index=np.arange(n)


def Adaboost(numh, iniweight,label,Npos,features,index):
	weight=iniweight
	alpha=np.zeros(numh)
	thres=np.zeros(numh)
	polarity=np.zeros(numh)
	idx=np.zeros(numh)
	for l in xrange(numh):
		a=getWeakClassifier(features[index], weight, label, Npos)
		currentMin=a[0]
		alpha[l]=np.log((1-currentMin)/currentMin)/2
		result=a[4]
		idx[l]=index[a[3]]
		index=np.delete(index,a[3])
		print idx[l]
		print a[3]
		thres[l]=a[1]
		polarity[l]=a[2]
		weight=reweight(weight,currentMin,result,label)
	return alpha, adafeature, thres, polarity,idx

numh=400



k=Adaboost(numh, iniweight,label,2000)
alpha=k[0]
adafeature1=k[1]
thres=k[2]
polarity=k[3]
idxx=k[4]

np.savetxt('adaboost.txt',k)


def cascadedclassifier(feature,f,Ftarget):
	n=1
	cascaded=[]
	theta=[]
	alphah=np.zeros(feature.shape[1])
	#用i个feature组成classifier看是否满足条件
	for i in xrange(feature.shape[0]):
		#对每一个样本 计算i个feature组成的classifier sum(alpha_t*h_t)的结果以及 fpr
		for j in xrange(feature.shape[1]):
			alphah[j]=alphah[j]+(feature[i,j]-thres[i])*alpha[i]*polarity[i]
		#对该i个 feature 组成的 classifier 设定theta使得detection rate ＝1
		theta1=np.min(alphah[0:2000])
		#计算该theta下的fpr
		fpr=np.mean(alphah[Npos:]>=theta1)
		print fpr
		print np.mean(alphah[Npos:])
		print np.mean(alphah[0:Npos])
		# record the index of images that have been correctly classified
		delindex=np.where(alphah>=theta1)
		#如果fpr小于f，添加该层为classifier 删掉未被错判的例子 focus on被错判的例子
		#如果fpr大于f，继续添加feature
		if fpr<=f:
			n=n+1
			# Record the number of features in this classifier
			cascaded.append(i)
			# Record the threshold theta which guarantees detection rate 1.
			theta.append(theta1)
			# Delete nonface images that have benn classified correctly
			feature=np.delete(feature,delindex[0][2000:],1)
			alphah=np.delete(alphah,delindex[0][2000:])
			if np.power(f,n)<1-Ftarget:
				break
	return cascaded,theta
cascaded,theta=cascadedclassifier(features,0.3,0.999)

# k级classifier
def classifier(Img,k):
	intImg=np.zeros((row+1,col+1))
	intImg[1:row+1,1:col+1]=np.cumsum(cumsum(Img,axis=0),axis=1)
	xfeature=np.zeros(cascaded[k]+1)
	for i in xrange(cascaded[k]+1):
		rec1=rect1loc[int(featureId[i])]
		rec2=rect2loc[int(featureId[i])]
		xfeature[i]=sumRect(intImg,rec1)-sumRect(intImg,rec2)
	h=0
	for i in xrange(cascaded[k]+1):
		h=h+(xfeature[i]-thres[i])*alpha[i]*polarity[i]
	if h>theta[k]:
		return 1
	else:
		return 0