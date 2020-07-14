echo "Starting to deploy springboot-k8s-mysql"
gcloud container clusters get-credentials ${cluster-name} --zone us-central1-c --project ${project-id}
echo "Finish to get-credentials"

gcloud builds submit --tag gcr.io/${project-id}/springboot-k8s-mysql
echo "Finish build and push to container registery"

cd src/main/resources/

kubectl apply -f mysqldb-credentials.yml
echo "Finish  from mysqldb-credentials"

kubectl apply -f mysql-deployment.yml
echo "Finish from  mysql-deployment"

kubectl apply -f deployment.yaml
echo "Finished  deployment"
