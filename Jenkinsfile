node {
    stage 'Clone the project'
    git 'https://github.com/miguelsd0501/t3-microservice-testing.git'
   
        stage("Compilation and Analysis") {
            parallel 'Compilation': {
                sh "./mvnw clean install -DskipTests"
            }, 'Static Analysis': {
                stage("Checkstyle") {
                    environment {
                        scannerHome = tool 'SonarQubeScanner'
                    }
                    
                    steps {
                    withSonarQubeEnv('sonarqube') {
                        sh "${scannerHome}/bin/sonar-scanner"
                    }

                    timeout(time: 10, unit: 'MINUTES') {
                        waitForQualityGate abortPipeline: true
                    }
                    }
                }
            }
        }
         

            stage("Runing unit tests") {
                    try {
                        sh "./mvnw test -Punit"
                    } catch(err) {
                        step([$class: 'JUnitResultArchiver', testResults: 
                          '**/target/surefire-reports/TEST-*UnitTest.xml'])
                        throw err
                    }
                   step([$class: 'JUnitResultArchiver', testResults: 
                     '**/target/surefire-reports/TEST-*UnitTest.xml'])
             }
             
            stage("Deployment") {
                
                withEnv(['JENKINS_NODE_COOKIE=dontkill']) {
                    sh 'nohup ./mvnw spring-boot:run -Dspring-boot.run.arguments=--server.port=9001 &'
                }   
            }

}
