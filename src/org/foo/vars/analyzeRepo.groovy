def call(String repoName) {
    def bar = new org.foo.Bar(this)
    bar.analyzeRepo(repoName)
}
