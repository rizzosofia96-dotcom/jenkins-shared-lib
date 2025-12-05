// vars/analyzeRepo.groovy

def call(String repoUrl, String branch = 'main') {
    def bar = new org.foo.Bar(this)  // passo il contesto 'steps' a Bar
    bar.cloneAndAnalyze(repoUrl, branch)
}

