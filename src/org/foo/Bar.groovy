package org.foo

class Bar implements Serializable {
    def steps

    Bar(steps) {
        this.steps = steps
    }

    def cloneAndAnalyze(String repoUrl, String branch = 'main') {
        // Stampa un messaggio di log che indica quale repo e branch verranno clonati
        steps.echo "Cloning repo ${repoUrl} branch ${branch}"

        // Effettua il checkout del repository Git specificato, sul branch indicato
        steps.checkout([
            $class: 'GitSCM',
            branches: [[name: branch]],
            userRemoteConfigs: [[url: repoUrl]]
        ])

        // Stampa un messaggio di log per indicare l'inizio dell'analisi SonarQube
        steps.echo "Running SonarQube analysis"

        // Avvia il blocco con l'ambiente di SonarQube configurato su Jenkins con il nome 'sonarqube'
        steps.withSonarQubeEnv('sonarqube') {
            // Esegue il comando sonar-scanner in ambiente Windows tramite 'bat'
            steps.bat '''
                sonar-scanner ^
                  -Dsonar.projectKey=Gestionerubrica ^
                  -Dsonar.sources=. ^
                  -Dsonar.java.binaries=.
            '''
        }
    }
}
