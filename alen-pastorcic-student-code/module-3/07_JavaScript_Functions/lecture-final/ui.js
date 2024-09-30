document.addEventListener('DOMContentLoaded', () => {
  if (typeof multiplyTogether !== 'undefined') {
    document.getElementById('multiply-together').textContent = multiplyTogether(66, 87);
    document.getElementById('multiply-nothing').textContent = multiplyTogether();
  }
  if (typeof multiplyNoUndefined !== 'undefined') {
    document.getElementById('multiply-defaults').textContent = multiplyNoUndefined();
  }

  let givenArray = [3, 7, 12, 16, 15, 21, 26];
  document.querySelectorAll('span.given-array').forEach((element) => {
    element.textContent = JSON.stringify(givenArray);
  });
  if (typeof sumAllNumbers !== 'undefined') {
    document.getElementById('sum-all-numbers').textContent = sumAllNumbers(givenArray);
  }
  if (typeof allDivisibleByThree !== 'undefined') {
    document.getElementById('divisible-by-three').textContent = JSON.stringify(
      allDivisibleByThree(givenArray)
    );
  }
});
