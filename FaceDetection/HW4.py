import numpy as np 
from numpy import cumsum, matlib
import glob
from scipy import misc
from __future__ import division

def getHaar (row, col, Npos, Nneg):
	Nimg = Npos + Nneg
	Nfeatures = 52200 #change this number if you need to use more/less features 
	features = np.zeros((Nfeatures, Nimg))
	
	files = glob.glob("/Users/apple/Desktop/UChi/37710/facedetection/faces/*.jpg")
	print len(files)
	for i in xrange (len(files)):
		print i
		imgGray = misc.imread (files[i], flatten=1) #array of floats, gray scale image
		if (i < Npos):
			intImg = np.zeros((row+1,col+1))
			intImg [1:row+1,1:col+1] = np.cumsum(cumsum(imgGray,axis=0),axis=1)	
			features [:,i] = computeFeature(intImg,row,col,Nfeatures) 

			
	files = glob.glob("/Users/apple/Desktop/UChi/37710/facedetection/background/*.jpg")
	for i in xrange (len(files)):
		print i 
		imgGray = misc.imread (files[i], flatten=1) #array of floats, gray scale image
		if (i < Nneg):
			intImg = np.zeros((row+1,col+1))
			intImg [1:row+1,1:col+1] = np.cumsum(cumsum(imgGray,axis=0),axis=1)
			features[:,i+Npos] = computeFeature(intImg,row,col,Nfeatures)
			
	#print "feat ", features[1000,:]
	return features
		

def sumRect(I, rect_four): 
	
	row_start = rect_four[0]
	col_start = rect_four[1] 
	width = rect_four[2]
	height = rect_four[3] 
	one = I[row_start-1, col_start-1]
	two = I[row_start-1, col_start+width-1]
	three = I[row_start+height-1, col_start-1]
	four = I[row_start+height-1, col_start+width-1]
	rectsum = four + one -(two + three)
	return rectsum 


def computeFeature(I, row, col, numFeatures): 
	feature = np.zeros(numFeatures)
	cnt = 0 # count the number of features 
	window_h = 1; window_w=2 #window/feature size 
	for h in xrange(8,int(row/window_h+1),2): #extend the size of the rectangular feature
		for w in xrange(8,int(col/window_w+1),2):
			for i in xrange (1,int(row+1-h*window_h+1),4): #stride size=4
				for j in xrange(1,int(col+1-w*window_w+1),4): 
					rect1=np.array([i,j,w,h]) #4x1
					rect2=np.array([i,j+w,w,h])
					feature [cnt]=sumRect(I, rect2)- sumRect(I, rect1) 
					cnt=cnt+1
	window_h = 2; window_w=1 
	for h in xrange(8,int(row/window_h+1),2): 
		for w in xrange(8,int(col/window_w+1),2):
			for i in xrange (1,int(row+1-h*window_h+1),4):
				for j in xrange(1,int(col+1-w*window_w+1),4):
					rect1=np.array([i,j,w,h])
					rect2=np.array([i+h,j,w,h])
					feature[cnt]=sumRect(I, rect1)- sumRect(I, rect2)
					cnt=cnt+1	
	window_h = 1; window_w=2
	for h in xrange(8,int(row/window_h+1),2): 
		for w in xrange(8,int(col/window_w+1),2):
			for i in xrange (1,int(row+1-h*window_h+1),4):
				for j in xrange(1,int(col+1-2*w*window_w+1),4):
					rect1=np.array([i,j,w,h])
					rect2=np.array([i,j+w,w,h])
					rect3=np.array([i,j+2*w,w,h])
					feature[cnt]=sumRect(I, rect1)+sumRect(I, rect3)- sumRect(I, rect2)
					cnt=cnt+1
	window_h = 2; window_w=1
	for h in xrange(8,int(row/window_h+1),2): 
		for w in xrange(8,int(col/window_w+1),2):
			for i in xrange (1,int(row+1-2*h*window_h+1),4):
				for j in xrange(1,int(col+1-w*window_w+1),4):
					rect1=np.array([i,j,w,h])
					rect2=np.array([i+h,j,w,h])
					rect3=np.array([i+2*h,j,w,h])
					feature[cnt]=sumRect(I, rect1)+sumRect(I, rect3)- sumRect(I, rect2)
					cnt=cnt+1
	return feature
row=64
col=64	
n=0
rect2loc=[]
rect1loc=[]
rect3loc=[]
window_h = 1; window_w=2
for h in xrange(8,int(row/window_h+1),2): #extend the size of the rectangular feature
	for w in xrange(8,int(col/window_w+1),2):
		for i in xrange (1,int(row+1-h*window_h+1),4): #stride size=4
			for j in xrange(1,int(col+1-w*window_w+1),4): 
				rect1=np.array([i,j,w,h]) #4x1
				rect2=np.array([i,j+w,w,h])
				rect3=np.array([0,0,0,0])
				rect1loc.append(rect1)
				rect2loc.append(rect2)
				rect3loc.append(rect3)
				n=n+1
n
window_h = 2; window_w=1 
for h in xrange(8,int(row/window_h+1),2): 
	for w in xrange(8,int(col/window_w+1),2):
		for i in xrange (1,int(row+1-h*window_h+1),4):
			for j in xrange(1,int(col+1-w*window_w+1),4):
				rect1=np.array([i,j,w,h])
				rect2=np.array([i+h,j,w,h])
				rect3=np.array([0,0,0,0])
				rect1loc.append(rect1)
				rect2loc.append(rect2)
				rect3loc.append(rect3)
				n=n+1
n
window_h = 1; window_w=2
for h in xrange(8,int(row/window_h+1),2): 
	for w in xrange(8,int(col/window_w+1),2):
		for i in xrange (1,int(row+1-h*window_h+1),4):
			for j in xrange(1,int(col+1-2*w*window_w+1),4):
				rect1=np.array([i,j,w,h])
				rect2=np.array([i,j+w,w,h])
				rect3=np.array([i,j+2*w,w,h])
				rect1loc.append(rect1)
				rect2loc.append(rect2)
				rect3loc.append(rect3)
				n=n+1
n				
window_h = 2; window_w=1
for h in xrange(8,int(row/window_h+1),2): 
	for w in xrange(8,int(col/window_w+1),2):
		for i in xrange (1,int(row+1-2*h*window_h+1),4):
			for j in xrange(1,int(col+1-w*window_w+1),4):
				rect1=np.array([i,j,w,h])
				rect2=np.array([i+h,j,w,h])
				rect3=np.array([i+2*h,j,w,h])
				rect1loc.append(rect1)
				rect2loc.append(rect2)
				rect3loc.append(rect3)
				n=n+1
n

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








np.savetxt('features.txt', features)








numh=200

Nimg=features.shape[1]
iniweight = np.zeros((Nimg,1))
iniweight[:,0] = 1/Nimg


#label face as 1, nonface as 0.
label = np.zeros((400,1))
label[:200] = 1

weight=iniweight
alpha=np.zeros(numh)
thres=np.zeros(numh)
polarity=np.zeros(numh)
idx=np.zeros(numh)
index=np.arange(features.shape[0])
result=[]
for l in xrange(numh):
	a=getWeakClassifier(features[index], weight, label, Npos)
	currentMin=a[0]
	alpha[l]=np.log((1-currentMin)/currentMin)/2
	result.append(a[4])
	idx[l]=index[a[3]]
	index=np.delete(index,a[3])
	print idx[l]
	print a[3]
	thres[l]=a[1]
	polarity[l]=a[2]
	weight=weight*np.exp(-alpha[l]*(-1)**(1-(a[4]==label)))
	weight=weight/np.sum(weight)
	print l






aaa=[features,result,thres, polarity,alpha,idx]
np.savetxt('k.txt', k)


weight=iniweight
alpha=np.zeros(numh)
thres=np.zeros(numh)
polarity=np.zeros(numh)
idx=np.zeros(numh)










feature=np.copy(features[idx.astype(int)])
n=1
cascaded=[]
theta=[]
#labela=np.copy(label)
#resulta=np.copy(result)
alphah=np.zeros(feature.shape[1])
#用i个feature组成classifier看是否满足条件
for i in xrange(feature.shape[0]):
	#对每一个样本 计算i个feature组成的classifier sum(alpha_t*h_t)的结果以及 fpr
	for j in xrange(feature.shape[1]):
		alphah[j]=alphah[j]-(-1)**result[i][j][0]*alpha[i]
	#对该i个 feature 组成的 classifier 设定theta使得detection rate ＝1
	#theta1=np.min(alphah[0:200])
	print alphah
	theta1=np.percentile(alphah[0:200],0.5)
	#计算该theta下的fpr
	delindex=np.where(alphah>=theta1)[0]
	count=0
	for k in xrange(feature.shape[1]):
		if(label[k]==0 and alphah[k]>=theta1):
			count=count+1
	fpr=count/feature.shape[1]	
	print fpr
	print np.mean(alphah[Npos:])
	print np.mean(alphah[0:Npos])
	# record the index of images that have been correctly classified
	#delindex=np.where(alphah>=theta1)[0]
	delindex=delindex[delindex>=200]
	#如果fpr小于f，添加该层为classifier 删掉未被错判的例子 focus on被错判的例子
	#如果fpr大于f，继续添加feature
	if fpr<=0.3:
		n=n+1
		# Record the number of features in this classifier
		cascaded.append(i)
		# Record the threshold theta which guarantees detection rate 1.
		theta.append(theta1)
		# Delete nonface images that have benn classified correctly
		feature=np.delete(feature,delindex,1)
		alphah=np.delete(alphah,delindex)
		result=np.delete(result,delindex,1)
		print feature.shape
		print alphah.shape
		if np.power(0.3,n)<1-0.999:
			break

cascaded,theta=cascadedclassifier(features[idx],0.5,0.99)

# k级classifier
def classifier(Img,k):
	intImg=np.zeros((row+1,col+1))
	intImg[1:row+1,1:col+1]=np.cumsum(cumsum(Img,axis=0),axis=1)
	print intImg.shape
	xfeature=np.zeros(cascaded[k]+1)
	for i in xrange(cascaded[k]+1):
		rec1=rect1loc[int(idx[i])]
		rec2=rect2loc[int(idx[i])]
		rec3=rect3loc[int(idx[i])]
		print rec3
		print rec2
		print rec1
		if rec3[2]==0:
			xfeature[i]=sumRect(intImg,rec1)-sumRect(intImg,rec2)
		else:
			xfeature[i]=sumRect(intImg,rec1)+sumRect(intImg,rec3)-sumRect(intImg,rec2)
	h=0
	for i in xrange(cascaded[k]+1):
		h=h+(xfeature[i]-thres[i])*alpha[i]*polarity[i]
	if h>theta[k]:
		return 1
	else:
		return 0

testpic=misc.imread ("/Users/apple/Desktop/UChi/37710/facedetection/class.jpg", flatten=1)


patch=[]
windloc=[]
n=0
for i in xrange(1,testpic.shape[0]-64,16):
	for j in xrange(1,testpic.shape[1]-64,16):
		patch.append(testpic[i:i+64,j:j+64])
		windloc.append([i,j])
		n=n+1
indct=np.zeros(n)
indct=indct+1
i=0
m=0
for i in xrange(n):
	for k in xrange(len(cascaded)):
		indct[i]=classifier(patch[i],k)
		if indct[i]==0:
			break
windloc[np.where(indct==1)[0]]


for n in xrange(len(patch)):
	indct[n]=classifier(patch[n])




for k in xrange(len(cascaded)):
	for j in xrange(n):
		if indct[j]!= 0:
			indct[j]=classifier(patch[j],k)
windloc[np.where(indct==1)[0],1:2]


patch=[]
windloc=[]
n=0
for i in xrange(1,testpic.shape[0]-64,16):
	for j in xrange(1,testpic.shape[1]-64,16):
		patch.append(testpic[i:i+64,j:j+64])
		windloc.append([i,j])
		n=n+1
indct1=np.zeros(n)
indct1=indct1+1
i=0
m=0
indc=np.zeros(n)
for i in xrange(len(patch)):
	print i
	indct1[i]=classifier(patch[n])





for i in xrange(400):
	for j in xrange(200):



















#Example usage: 

row=64; col=64 #image size 
Npos = 10 #number of face images
Nneg = 10 #number of background images

features= getHaar("./smalldata/", row, col, Npos, Nneg)




