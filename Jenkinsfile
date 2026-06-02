pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'chmod +x mvnw'
                sh './mvnw clean package -DskipTests'
            }
        }
        stage('Deploy') {
            steps {
                sh '''
                    scp -i /var/jenkins_home/.ssh/id_rsa \
                    -o StrictHostKeyChecking=no \
                    target/*.jar ubuntu@52.62.48.156:/home/ubuntu/app.jar
                    
                    ssh -i /var/jenkins_home/.ssh/id_rsa \
                    -o StrictHostKeyChecking=no \
                    ubuntu@52.62.48.156 \
                    "pkill -f app.jar || true && nohup java -jar /home/ubuntu/app.jar > /home/ubuntu/app.log 2>&1 &"
                '''
            }
        }
    }
}