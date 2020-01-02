#!groovy
pipeline {
  agent any
  stages {
     stage ('Artifactory configuration') {
            steps {
                rtServer (
                    id: "artifactory",
                    url: "http://149.210.159.139:8081/artifactory/"
                )

                rtMavenDeployer (
                    id: "MAVEN_DEPLOYER",
                    serverId: "artifactory",
                    releaseRepo: "libs-release-local",
                    snapshotRepo: "libs-snapshot-local"
                )

                rtMavenResolver (
                    id: "MAVEN_RESOLVER",
                    serverId: "artifactory",
                    releaseRepo: "libs-release",
                    snapshotRepo: "libs-snapshot"
                )
            }
        }

        stage ('Exec Maven') {
		
            steps {
                rtMavenRun (
                    tool: 'maven-3.6.3', // Tool name from Jenkins configuration
                    pom: './pom.xml',
                    goals: 'clean install',
                    /*deployerId: "MAVEN_DEPLOYER",*/
                    resolverId: "MAVEN_RESOLVER"
                )
            }
        }
        
        stage ('Docker Build'){
                  steps {
                      sh 'docker build -t dhohle/transliterator:latest .'
                  }
              }
      stage ("Docker Push"){
             steps{
    withCredentials([ usernamePassword(credentialsId: 'dockerHub', passwordVariable: 'dockerHubPassword', 
    	usernameVariable: 'dockerHubUser')]){
                     sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                     sh "docker push dhohle/transliterator:latest"
                  }
			}
	}
        /*stage ('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: "artifactory"
                )
            }
        }*/
    }
  
}