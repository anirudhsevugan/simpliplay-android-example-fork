# contrib
This branch is for contributions to the source code. The source code should be read from the *main* branch. Any contributions, or modified versions of the same software will be here,
with different folders for different authors. **You should NOT make pull requests for contributions in the main branch, you should make it in this branch after you've created a fork with your modifications**. 

There should be **one fork** for each contributor, or a team of contributors. This fork should be modified with all the modifications you made. **You should not make a new fork for each modification**. **Continue using the old one**.

If your contribution pleases reviewers enough, it will eventually be added to the *main* branch and be included with **all** releases of the app from then.

## Rules
1. Contributions should only contain the source code, and should NOT contain build files, or folders like *android/*. For example, if you're editing the Flutter release, only submit the content in the *lib/* folder, where all text-based source code for the app is stored.

2. Contributions should NOT contain any errors. To test any errors in your source code, try compiling your app. In the AI2 version, any errors would be either runtime errors, bugs that cause the app to freeze or unexpectedly stop, or duplicate event blocks. In the Flutter version, any errors would either be syntax errors, bugs that cause the app to freeze or unexpectedly stop, or runtime errors.
