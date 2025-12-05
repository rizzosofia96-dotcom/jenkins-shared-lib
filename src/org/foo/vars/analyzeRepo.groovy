def call(String repoName) {
    echo "Calling analyzeRepo with repoName: ${repoName}"
    def bar = new org.foo.Bar(this)
    bar.analyzeRepo(repoName)
}
