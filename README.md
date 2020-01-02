# Mahallem-Backend
# Mahallem
Mahallem - React.js / Java(Spring Framework) / Rest 
[![Build Status](https://travis-ci.com/guvense/Mahallem-Backend.svg?branch=master)](https://travis-ci.com/guvense/Mahallem-Backend)
![React](https://cdn4.iconfinder.com/data/icons/logos-3/600/React.js_logo-128.png)    ![Java](https://cdn2.iconfinder.com/data/icons/well-known-1/1024/Java-128.png)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

You need to install Java 1.8, Node.js and NPM, Maven, MongoDb

```
Installation on Mac:

brew update  
brew install node

The default Node.js will be installed under the /usr/local/Cellar/node directory on your system.
To check node.js version:

node -v

To check the installed version of NPM installed with Node.js.

npm -v

If you don't have any java version on your machine visit the below site and perform the installation step.
--> https://java.com/en/download/help/download_options.xml

You need to install maven. Follow the steps according to your OS
--> https://www.baeldung.com/install-maven-on-windows-linux-mac

Finally, you need to install mongodb from below link.
--> https://docs.mongodb.com/manual/installation/
```

### Initializing a local Git repository

```
git init
```

### Getting the project
```
git clone https://github.com/guvense/Mahallem-Backend.git
```

### Basic Commands

| Command | Description |
| ------- | ----------- |
| `git status` | Check status |
| `git add [file-name.txt]` | Add a file to the staging area |
| `git add -A` | Add all new and changed files to the staging area |
| `git commit -m "[commit message]"` | Commit changes |
| `git rm -r [file-name.txt]` | Remove a file (or folder) |

### Branching & Merging

| Command | Description |
| ------- | ----------- |
| `git branch` | List branches (the asterisk denotes the current branch) |
| `git branch -a` | List all branches (local and remote) |
| `git branch [branch name]` | Create a new branch |
| `git branch -d [branch name]` | Delete a branch |
| `git push origin --delete [branch name]` | Delete a remote branch |
| `git checkout -b [branch name]` | Create a new branch and switch to it |
| `git checkout -b [branch name] origin/[branch name]` | Clone a remote branch and switch to it |
| `git branch -m [old branch name] [new branch name]` | Rename a local branch |
| `git checkout [branch name]` | Switch to a branch |
| `git checkout -` | Switch to the branch last checked out |
| `git checkout -- [file-name.txt]` | Discard changes to a file |
| `git merge [branch name]` | Merge a branch into the active branch |
| `git merge [source branch] [target branch]` | Merge a branch into a target branch |
| `git stash` | Stash changes in a dirty working directory |
| `git stash clear` | Remove all stashed entries |

### Sharing & Updating Projects

| Command | Description |
| ------- | ----------- |
| `git push origin [branch name]` | Push a branch to your remote repository |
| `git push -u origin [branch name]` | Push changes to remote repository (and remember the branch) |
| `git push` | Push changes to remote repository (remembered branch) |
| `git push origin --delete [branch name]` | Delete a remote branch |
| `git pull` | Update local repository to the newest commit |
| `git pull origin [branch name]` | Pull changes from remote repository |
| `git remote add origin ssh://git@github.com/[username]/[repository-name].git` | Add a remote repository |
| `git remote set-url origin ssh://git@github.com/[username]/[repository-name].git` | Set a repository's origin branch to SSH |


### Inspection & Comparison

| Command | Description |
| ------- | ----------- |
| `git log` | View changes |
| `git log --summary` | View changes (detailed) |
| `git log --oneline` | View changes (briefly) |
| `git diff [source branch] [target branch]` | Preview changes before merging |

#### The seven rules of a great Git commit message

* Separate subject from body with a blank line
```
Example:
Fix typo in introduction to user guide
```
* Limit the subject line to 50 characters
```
shoot for 50 characters, but consider 72 the hard limit.
```
* Capitalize the subject line
```
Example:
Accelerate to 88 miles per hour
```
* Do not end the subject line with a period
```
Use this:
Open the pod bay doors

Instead of 
Open the pod bay doors.
```
* Use the imperative mood in the subject line
```
Imperative mood just means “spoken or written as if giving a command or instruction”.
For a few example:
 - Remove the unused variables
 - Fix typo in introduction to user guide
 - Update getting started documentation
```
* Wrap the body at 72 characters
```
Example:
Fix typo in introduction to user guide
```
* Use the body to explain what and why vs. how


## Authors
* **Guven SECKIN** [profile](https://github.com/guvense)
* **Gokhan YILMAZ** [profile](https://github.com/GokhanYilmaz44)
* **Sercan KAL** [profile](https://github.com/srcnkl)

See also the list of [contributors](https://github.com/guvense/Mahallem-Backend/graphs/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

