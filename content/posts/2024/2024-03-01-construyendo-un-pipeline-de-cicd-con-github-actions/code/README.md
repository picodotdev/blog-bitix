# platform-github-actions-workflows

The CI/CD pipeline for any project using [Github Actions](https://docs.github.com/en/actions). Contains a collection of reusable
Github Actions workflows that any other project can use to build and deploy.

Uses [Task](https://taskfile.dev/) to define project tasks commands, so the same tasks names can be used in any project, be able to give
the workflows the option to customize the commands, makes the tasks code language agnostic and possible to have the same tasks names for
Java and Node projects so any engineer that knows the task names convention be able to use any code repository.

Also uses [semantic-release](https://semantic-release.gitbook.io/semantic-release/) to generate the release tagging in git the released code
version based on pull request commits messages.

## Usage

To use the reusable workflows the *uses* clause must be used providing in the caller workflow the necessary input parameters and secrets for
called workflow.

Each caller repository must define its custom *main.yml* workfile and its custom *taskfile.yml*. For *build* job the caller repository must
define its checks tasks that should include static code analysis and unit tests.

See and [example workflow file](.github/workflows/main.yml) to use in a caller repository and an [example task file](taskfile.yml).

Supported artifact types:

* dockerfile
* java-library

Supported runtimes:

* gke (Google Kubernetes Engine)
* gae (Google App Engine)

## Installation

To install Task.

```bash
$ brew install go-task
```

To install node and semantic-release:

```bash
$ nvm install v20.11.1
$ npm install --save-dev semantic-release
$ npm install --save-dev @semantic-release/git
$ npm install --save-dev @semantic-release/exec
```

## Workflow

Uses

* CI/CD: Github Actions
* Tools: Task, semantic-release

## Documentation

Github Actions reference

* [Reusing workflows](https://docs.github.com/en/actions/using-workflows/reusing-workflows)
* [Creating a composite action](https://docs.github.com/en/actions/creating-actions/creating-a-composite-action)
* [Using starter workflows](https://docs.github.com/en/actions/learn-github-actions/using-starter-workflows)
* [Creating starter workflows for your organization](https://docs.github.com/en/actions/using-workflows/creating-starter-workflows-for-your-organization)
* [Creating custom deployment protection rules](https://docs.github.com/en/actions/deployment/protecting-deployments/creating-custom-deployment-protection-rules)
* [Managing a merge queue](https://docs.github.com/en/repositories/configuring-branches-and-merges-in-your-repository/configuring-pull-request-merges/managing-a-merge-queue)
* [starter-workflows](https://github.com/actions/starter-workflows)
* [starter-workflows/deployments](https://github.com/actions/starter-workflows/tree/main/deployments)
* [starter-workflows/ci](https://github.com/actions/starter-workflows/tree/main/ci)

Semantic release

* [Commit message format](https://semantic-release.gitbook.io/semantic-release/)

Links

* [Continuous Delivery Patterns and Anti-Patterns](https://dzone.com/refcardz/continuous-delivery-patterns)
* [Software Delivery Guide](https://martinfowler.com/delivery.html)
* [Continuous delivery pipeline 101 ](https://www.atlassian.com/continuous-delivery/principles/pipeline)
* [What is Continuous Delivery?](https://continuousdelivery.com/)
* [CI/CD Process: Flow, Stages, and Critical Best Practices](https://codefresh.io/learn/ci-cd-pipelines/ci-cd-process-flow-stages-and-critical-best-practices/)
* [Semantic Versioning 2.0.0](https://semver.org/)

Books

* [Continuous Delivery: Reliable Software Releases through Build, Test, and Deployment Automation](https://www.amazon.com/Continuous-Delivery-Deployment-Automation-Addison-Wesley/dp/0321601912)
* [Learning GitHub Actions: Automation and Integration of CI/CD With GitHub](https://www.amazon.es/Learning-Github-Actions-Automation-Integration/dp/109813107X/)
