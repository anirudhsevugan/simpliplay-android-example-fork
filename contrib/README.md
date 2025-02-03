# contrib

## How to contribute
   First, create a new fork of the *contrib* branch of the repo.
   Second, add a new folder to your repo (don't delete existing folders, doing this will hurt other people's contributions), and name it something like *[your-username]*.
   Third, add your modified source code to that folder (refer to the *main* branch for the original source code to modify).
   Fourth, submit a pull request to the repository in the *contrib* branch. You might have to wait awhile until it gets approved and your fork gets merged with the branch.
   Five, that's pretty much it! Enjoy your day!

## About this branch
This branch is for contributions to the source code. The source code should be read from the *main* branch. Any contributions, or modified versions of the same software will be here,
with different folders for different authors. **You should NOT make pull requests for contributions in the main branch, you should make it in this branch after you've created a fork with your modifications**. 

There should be **one fork** for each contributor, or a team of contributors. This fork should be modified with all the modifications you made. **You should not make a new fork for each modification**. **Continue using the old one**.

If your contribution pleases reviewers enough, it will eventually be added to the *main* branch and be included with **all** releases of the app from then.

## Rules
1. Contributions should only contain the source code, and should NOT contain build files, or folders like *android/*. For example, if you're editing the Flutter release, only submit the content in the *lib/* folder, where all text-based source code for the app is stored.

2. Contributions should NOT contain any errors. To test any errors in your source code, try compiling your app. In the AI2 version, any errors would be either runtime errors, bugs that cause the app to freeze or unexpectedly stop, or duplicate event blocks. In the Flutter version, any errors would either be syntax errors, bugs that cause the app to freeze or unexpectedly stop, or runtime errors.
