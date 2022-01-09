//equivalent to home.js
//possibly get full name from database? or just use first name

let mainDiv = document.getElementById('info');

if (loggedInUser) {
    mainDiv.innerHTML = `<h3>Welcome, ${loggedInUser.fullName}!</h3>
    <p>Thanks for visiting PetApp. Here's a guide to what you can do here: </p>
    <ul>
        <li>Looking to adopt a new friend? Try "Available Pets".</li>
        <li>Want to give your pets some love? Try "My Pets".</li>
        <li>Need to change some account information? Try clicking on your username.</li>
    </ul>`;
} else {
    mainDiv.innerHTML = ``;
}