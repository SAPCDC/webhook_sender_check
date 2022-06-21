var imported = document.createElement('script');
imported.src = 'https://cdns.gigya.com/js/gigya.js?apiKey=4_zU_l5sFaCUWaU5W-0KFDzw';
document.head.appendChild(imported);

function getinfo(message)
{
    if(Action="accountUpdated")
    {
        var UID = message.data.uid;
var params = {
    "UID":UID,
    "include":"identities-active,identities-all,identities-global,loginIDs,emails,profile,data, password,lastLoginLocation, regSource,irank,rba,subscriptions,userInfo",
    "extraProfileFields":"languages,address,phones, education, honors, publications, patents, certifications, professionalHeadline, bio, industry, specialties, work, skills, religion, politicalView, interestedIn, relationshipStatus, hometown, favorites, followersCount, followingCount, username, locale, verified, timezone, likes, samlData",
    callback: getAccountInfoResponse 
}
 gigya.accounts.getAccountInfo(params);
}
}

function getAccountInfoResponse(response) {
    if (response.errorCode == 0) {
        
        var UID = response.UID;
        let DBName = "Users";
        let Table = "Users_Info";
        //var Data = response;
        //alert(firstName);
        var Data = JSON.parse(JSON.stringify(response));
        
        UpdateData(DBName, Table, Data, UID);
    }
}
