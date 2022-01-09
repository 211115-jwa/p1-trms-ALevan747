//equivalent to mypets.js
//here processing requests will be handled
//this will need tweaking to ensure functionality
//login fuinction needed to ensure the user is logged in to be able to request a reimbursement 
checkLogin().then(() => {
    console.log(loggedInUser);
    if (loggedInPerson.pets || loggedInPerson.requests.size > 0)
        showPets(loggedInPerson.requests)
    else {
        document.getElementById('Requests').remove();

        let noRequestsMsg = document.createElement('p');
        noRequestsMsg.innerText = 'You do not have any Requests made at this time!';
        document.getElementsByTagName('main')[0].appendChild(noRequestsMsg);
    }
});


 function getRequests() {
    let userInput = document.getElementById('dataInput'); 

    for(let request of requests){
        let rowForRequest = document.createElement('tr');

        for(let field in request){
            let rowForRequest = document.createElement('td');
            if(field !== 'status'){
                column.innerText = request[field];
            }
            rowForRequest.appendChild(column);
        }
        RequestTable.appendChild(rowForRequest);
    }
}