library(ape)
library(geoR)


rides <-read.csv("cta.csv", sep=",", header = T)
head(rides, n=147)
rides.dists <- as.matrix(dist(cbind(rides$Longitude, rides$Latitude)))
rides.dists.inv <- 1/rides.dists
diag(rides.dists.inv) <- 0
rides.dists.inv[1:146, 1:146]
Moran.I(rides$Total, rides.dists.inv)


dists <- dist(rides[,3:4])
summary(dists)
 breaks = seq(0, 0.4, l = 30)
v1 <- variog(coords = rides[,3:4], data = rides[,5], breaks = breaks)

v1.summary <- cbind(c(1:147), v1$v, v1$n)
colnames(v1.summary) <- c("lag", "semi-variance", "# of pairs")

v1.summary

plot(v1, type = "b", main = "Variogram: Rides")

