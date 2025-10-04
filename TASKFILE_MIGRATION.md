# Shell Scripts to Taskfile Migration

This document shows the mapping between the original shell scripts and the new Taskfile tasks.

## Quick Reference

| Shell Script | Task Command | Description |
|--------------|--------------|-------------|
| `./blog-generate-styles.sh` | `task generate-styles` | Generate CSS styles from LESS and Hugo chromastyles |
| `./site-build.sh` | `task build` | Build the Hugo site |
| `./site-deploy.sh` | `task deploy` | Deploy the site to GitHub Pages |
| `./site-clean-public.sh` | `task clean-public` | Clean the public directory and rebuild |
| `./server.sh` | `task server` | Run Hugo development server |
| `./site-install.sh` | `task install` | Initial setup - clone repository and install dependencies |
| `./site-update.sh` | `task update` | Update the deploy directory from remote |
| `./site-sync.sh` | `task sync` | Synchronize - update, build and deploy |
| `./new.sh <args>` | `task new -- <args>` | Create a new Hugo post |
| `./site-update-build-deploy.sh` | `task update-build-deploy` | Update, build and deploy the site |
| `./site-clean-deploy.sh` | `task clean-deploy` | Clean the deploy directory and rebuild |
| `./npm-update.sh` | `task npm-update` | Update npm dependencies |
| `./blog-reset-deploy.sh` | `task reset-deploy` | Reset the deploy directory with a fresh Git repository |
| `./blog-convert-logotypes-to-png.sh` | `task convert-logotypes` | Convert SVG logotypes to PNG using Inkscape |
| `./blog-git-small-changes.sh` | `task git-small-changes` | Commit and push small changes |
| `./blog-reset-master.sh` | `task reset-master` | Reset the master branch with a new Git repository |
| `./site-check-firefox.sh` | `task check-firefox` | Take screenshots of links using Firefox headless |
| `./site-check-links.sh` | `task check-links` | Check external links using linkchecker |
| `./blog-git-apply.sh` | `task git-apply` | Apply a Git patch from Downloads |

## Advantages of Using Taskfile

1. **Better documentation**: Each task has a description that shows up in `task --list`
2. **Dependency management**: Tasks can depend on other tasks (e.g., `build` depends on `generate-styles`)
3. **Cross-platform**: Works on Linux, macOS, and Windows
4. **Consistent interface**: All tasks use the same `task <name>` command structure
5. **Built-in features**: Includes features like dry-run (`--dry`), parallel execution, and more

## Installation

Install Task using one of these methods:

### Using Go
```bash
go install github.com/go-task/task/v3/cmd/task@latest
```

### Using the official installer (Linux/macOS)
```bash
sh -c "$(curl --location https://taskfile.dev/install.sh)" -- -d -b /usr/local/bin
```

### Using package managers
```bash
# Homebrew (macOS/Linux)
brew install go-task/tap/go-task

# Snap (Linux)
sudo snap install task --classic

# Chocolatey (Windows)
choco install go-task
```

## Usage Examples

```bash
# List all available tasks
task --list

# Run a task
task build

# Dry run (see what would be executed)
task build --dry

# Pass arguments to a task
task new -- posts/2025/my-new-post.md

# Run multiple tasks
task generate-styles build deploy
```

## Notes

- The shell scripts (*.sh files) are still present in the repository for backward compatibility, but the recommended approach is to use the Taskfile
- Some tasks that reference other scripts (e.g., `./build.sh` in `clean-public`) may need the shell scripts to remain in place
- Task runs in the project root directory by default, but individual tasks can specify a different working directory using the `dir:` attribute
