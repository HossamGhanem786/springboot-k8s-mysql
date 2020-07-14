echo "Starting to deploy springboot-k8s-mysql"
gcloud container clusters get-credentials springboot-k8s-mysql --zone us-central1-c --project jenkins-276120
echo "Finish to get-credentials"

gcloud builds submit --tag gcr.io/jenkins-276120/springboot-k8s-mysql
echo "Finish build and push to container registery"

cd src/main/resources/

kubectl apply -f mysqldb-credentials.yml
echo "Finish  from mysqldb-credentials"

kubectl apply -f mysql-deployment.yml
echo "Finish from  mysql-deployment"

kubectl apply -f deployment.yaml
echo "Finished  deployment"
