node {
    stage 'Clone the project'
    git 'https://github.com/miguelsd0501/t3-microservice-testing.git'
   
        stage("Compilation and Analysis") {
            parallel 'Compilation': {
                sh "./mvnw clean install -DskipTests"
            }, 'Static Analysis': {
                stage("SonarQube Scanner") {
                    withSonarQubeEnv('sonarqube') {
                      sh 'mvn sonar:sonar'
                    } // SonarQube taskId is automatically attached to the pipeline context
                    // No need to occupy a node
                    stage("Quality Gate"){
                      timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                        def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                        if (qg.status != 'OK') {
                          error "Pipeline aborted due to quality gate failure: ${qg.status}"
                        }
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
