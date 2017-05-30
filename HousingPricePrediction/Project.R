library(ggplot2)
library(ggmap)
library(maptools)
library(dplyr)
kc<-read.csv("kc_house_data.csv")
substr(as.character(kc$date[1]),1,4)

kc1<-kc[,c('zipcode','long','lat')]
kc1$mean<-0
for(zipcode in levels(as.factor(kc$zipcode)))
{
	kc1[kc1$zipcode==zipcode,]$mean<-mean(kc$price[kc$zipcode==zipcode])
}

seattle<-get_map(c(-122.1709,47.54784),zoom='auto',source="osm",color="bw")


ggmap(seattle)+
geom_point(aes(x=long,y=lat,color=mean),size=0.1,alpha=1,data=kc1)+
scale_color_gradient2(midpoint=1000000,low="#66CC00",mid="#FFCC33",high="#CC0033", space ="Lab" )


drop_<-c('id','date','lat','long')
kc2<-kc[,!(names(kc)%in% drop_)]
kc2$year<-1800
kc2$month<-13
for(i in 1:nrow(kc2))
{
	kc2[i,'year']<-substr(as.character(kc$date[i]),1,4)
	kc2[i,'month']<-substr(as.character(kc$date[i]),5,6)
	if(kc2$yr_renovated[i]==0)
	{
		kc2$yr_renovated[i]=kc2$yr_built[i]
	}
}
kc2$zipcode<-as.factor(kc2$zipcode)

mod<-lm(log(price)~.-floors-yr_renovated-sqft_lot15,data=kc2)
anova(mod)

monthly<-c()
month<-c()
for(y in c('2014','2015'))
{
	for(m in c('01','02','03','04','05','06','07','08','09','10','11','12'))
	{
		monthly<-c(monthly,mean((subset(kc3,month==m&year==y)$price)))
		month<-c(month,paste(y,m,sep=''))
	}
}


drop_<-c('id','date','zipcode')
kc3<-kc[,!(names(kc)%in% drop_)]
kc3$year<-1800
#kc3$month<-13
for(i in 1:nrow(kc3))
{
	kc3[i,'year']<-substr(as.character(kc$date[i]),1,4)
	#kc3[i,'month']<-substr(as.character(kc$date[i]),5,6)
	if(kc3$yr_renovated[i]==0)
	{
		kc3$yr_renovated[i]=kc3$yr_built[i]
	}
}
kc3$year<-as.numeric(kc3$year)
#kc3$month<-as.numeric(kc3$month)

kc4<-kc3[kc3$year==2015,]
kc5<-kc4[kc4$yr_built>=2000,]
set.seed(1234)
samp<-sample(1542,514)

kc6<-kc5[-samp,]
mod<-lm(log(price)~.-year-yr_renovated-sqft_basement-bedrooms-sqft_lot-floors-sqft_living15-long-bathrooms-sqft_lot15-condition-sqft_above,data=kc6)
anova(mod)

mod<-lm(log(price)~.-sqft_basement-year-long-sqft_above-sqft_living15-bedrooms-floors-yr_built-sqft_lot15,data=kc6)
mod1<-locfit(log(price)~sqft_living+waterfront+view+grade+yr_built+lat,data= kc6)

plot(fitted(mod1),residuals(mod1))

ggplot()+geom_point(aes(x=long,y=lat,color=price),size=1.5,alpha=0.5,data=kc6)+scale_color_gradient2(midpoint=2000000,low="#66CC00",mid="#FFCC33",high="#CC0033", space ="Lab" )
ggplot()

ggplot(data=kc5,aes(x=price))+
geom_histogram(binwidth=10000,aes(fill=..count..))+
scale_fill_gradient(low='#66CC00',high='#CC0033')

mod<-lm(log(price)~.-year-yr_renovated-sqft_basement-sqft_living15-sqft_lot15-floors-long-sqft_lot,data=kc6)
summary(mod)
locfit(log(price)~sqft_living+waterfront+view+grade+yr_built+lat+bedrooms+bathrooms+condition+sqft_above,data= kc6)


