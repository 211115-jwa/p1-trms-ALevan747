document.getElementById(`submitbutton`).onclick = submitRequest;

async function submitRequest() {
    let etp = document.getElementById("eventtype").value;
    let et = {
        "eventId": etp
    }
    let st = {
        "statusId": 4,
        "name": "Pending Sup",
        "approver": "Supervisor"
    };
    let gfid =  document.getElementById("gformat").value;
    let gf  = {
        "formatId":  gfid
    }
                
    let edate = document.getElementById("eventdate").value;
    let etime = String(document.getElementById("etime").value);
    etime = etime + ":01";
    
    let request = {
        "requestor": loggedInPerson,
        "eventDate": edate,
        "eventTime": etime,
        "description": desc,
        "cost": cost,
        "gradingFormat": gf,
        "eventType": et,
        "status":st,
        "submittedAt": ''
    };
    console.log(request);
    if(request.cost == "" || request.address == "" || request.city){
        alert("Please Enter Proper Form Data")
    }
    else{

       let formatter = new Intl.NumberFormat('en-US', {
            style: 'currency',
            currency: 'USD',
          });
       
       let tokenHeader = {"Token":loggedInPerson.id};
        let response = await fetch(reqAppUrl + 'requests', {
        method: 'POST',
        body: JSON.stringify(request)
        ,  headers:tokenHeader
        });
        if (response.status === 201) {
            cost = loggedInPerson.funds - costCalc(cost);
        alert("Request has been sent\r\n Pending Request is approved the \r\n balance of "
        + formatter.format(cost) + " will be left from your account\r\n  for the rest of the year.");
        }
        else
            alert("Something went wrong");
    }    

    function costCalc(c){
        if (etp == 1 ){
            c = c * .80;
        }else if (etp == 2){
            c = c * .6;
        }else if (etp == 3){
            c = c * .75;
        }else if (etp == 4){
            c = c * 1;
        }else if (etp == 5){
            c = c * .9;
        }else 
            c = c * .3;
        return c;
         }


}