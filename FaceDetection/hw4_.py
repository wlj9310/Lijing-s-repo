def cascadedclassifier(features,alpha, featureId, thres, polarity,f,Ftarget):
	F=1
	n=1
	cascaded=[]
	theta=[]
	alphah=np.zeros(features.shape[1])
	for i in xrange(len(featureId)):
		for j in xrange(features.shape[1]):
			alphah[j]=alphah[j]+(features[int(featureId[i])][j]-thres[i])*alpha[i]*polarity[i]
			theta1=np.min(alphah[0:Npos])
		fpr=np.mean(alphah[Npos:]>=theta1)
		delindex=np.where(alphah>=theta1)
		if f<=0.3:
			n=n+1
			cascaded.append(j)
			theta.append(theta1)
			features=np.delete(features,delindex[0][2000:])
			alphah=np.delete(alphah,delindex[0][2000:])
			if np.power(f,n)>1-Ftarget:
				break
	return cascaded,theta
cascaded,theta=cascadedclassifier(features,alpha,featureId,thres,polarity,0.3,0.95)


def classifier(Img,featureId, thres, polarity,cascaded, theta,k):
	intImg=np.zeros((row+1,col+1))
	intImg[1:row+1,1:col+1]=np.cumsum(cumsum(Img,axis=0),axis=1)
	xfeature=np.zeros(len(featureId))
	for i in xrange(int(cascaded[k])):
		rec1=rect1loc[int(featureId[i])]
		rec2=rect2loc[int(featureId[i])]
		xfeature[i]=sumRect(intImg,rec1)-sumRect(intImg,rec2)
	h=0
	face=0
	for i in xrange(int(cascaded[k])):
		h=h+(xfeature[i]-thres[i])*alpha[i]*polarity[i]
	if h>theta:
		face=1 
	else:
		face=0
	return face


def classifier(Img,k):
	intImg=np.zeros((row+1,col+1))
	intImg[1:row+1,1:col+1]=np.cumsum(cumsum(Img,axis=0),axis=1)
	h=0
	for i in xrange(int(cascaded[k])):
		h=h+(xfeature[i]-thres[i])*alpha[i]*polarity[i]
	if h>theta:
		return 1 
	else:
		return 0


patch=[]
windloc=[]
n=0
for i in xrange(testpic.shape[0]-64,16):
	for j in xrange(testpic.shape[1]-64,16):
		patch.append(testpic[i:i+64,j:j+64])
		n=n+1
		windloc.append([n,i,j])
indct=np.zeros(n)
indct=indct+1
for k in xrange(len(cascaded)):
	for j in xrange(n):
		if indct[k]== 1:
			indct[k]=classifier(patch,k)
windloc[indct==1]






