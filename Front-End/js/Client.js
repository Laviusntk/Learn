var BASE_URL = "https://localhost";
var auth;
var Sites;
var foot;
var RepoResources;
var Resources;
var Assignments;


/*
* handles all request to the REST API. on successful request,
* the method calls the callback function.
*/
function request( _path, _payload, _callback){
  $.ajax({ url: BASE_URL + _path,
    headers: {
      'Access-Control-Allow-Origin': 'http://projectntk.000webhostapp.com',
      'data': JSON.stringify(_payload)
     }
  })
  .done(function(data){
    _callback(data);
  })
  .fail(function(data){
    console.log(JSON.stringify(data));
  });
}

/*
* Callback back function, called after a Login request
* to the REST API completed.
*/
var LoginCallback = function(data){
    console.log("Response Code : "+data.code);
    if(data.code === 200 || data.code === 201){
      console.log("Logged in");
      console.log("Fetching vula sites...");
      $("form").hide();
      $(".loader").show();
      requestSites();
      getRepositoryContent();
    }
    else{
      console.log("Error logging in");
      $("input").css("border","1px solid red");
      $("#login_err_msg").css("color","red");
      $("#login_err_msg").html("Credentials Incorrect, Please try again.");
    }
}
function Login(username, password){
  auth = {'_username':username, '_password':password};
  request(
    "/login",
    auth,
    LoginCallback
  );
}

var sitesCallBack = function(data){
  console.log("Vula sites recieved");
  //console.log(JSON.stringify(data));
  this.Sites = data;
  $(".loader").hide();
  $("#MainView").css("display","none");
  $("#ResourceView").css("display","block");
  renderVulaSites();
}
function requestSites(){
  request(
    "/sites",
    auth,
    sitesCallBack
  );
}

var siteResourceCallback = function(data){
  console.log(JSON.stringify(data));
  renderSiteResources(data);
}
function requestSiteResources(_siteid){
  request(
    "/site/resource?site_id="+_siteid,
    auth,
    siteResourceCallback
  );
}

var uploadCallBack = function(data){
  console.log("Response : \n"+JSON.stringify(data));
  console.log(data.code);
  if(data.code === 201 || data.code === 200){
    console.log("Upload success");
    $("#notification").append('<li><div class="alert-box success"><span>success: </span>'+data.message+' '+data.code+'<button class="close">x</button></li>');
  }
  else {
    $("#notification").append('<li><div class="alert-box error"><span>error: </span>'+data.message+' '+data.code+'<button class="close">x</button></li>');
  }
  $(".close").click(function(){
    $(this).parent().hide();
  });
  getRepositoryContent();
}
function uploadResource(_resource){
   auth.resource = _resource;
  request(
    "/ingest",
    auth,
    uploadCallBack
  );

}

/*
* Assignment upload callback. Gets the data from the REST API,
* checks the response code, from the response code then decides to
* render an error message or not. An error messsage is rendered in the case
* the reponse code is not 201 or 200, other wise a success message is displayed.
*/
var AssignmentUploadCallBack = function(data){
  console.log("Assignment Uploaded : "+JSON.stringify(data));
  if(data.code === 201 || data.code === 200){
    console.log("Upload success");
    $("#notification").append('<li><div class="alert-box success"><span>success: </span>'+data.message+' '+data.code+'<button class="close">x</button></li>');
  }
  else {
    $("#notification").append('<li><div class="alert-box error"><span>error: </span>'+data.message+' '+data.code+'<button class="close">x</button></li>');
  }
  $(".close").click(function(){
    $(this).parent().hide();
  });
  getRepositoryContent();
}
/*
* Assignment upload, sends a JSON object to the repository, before it does that, it adds
* an attribute pid to the Auth object. PID is constructed from the course acronym, year and the owner's ID.
*
*/
function uploadAssignment(_assignment){
  auth.assignment = _assignment;
  var course_acron = prompt("Please enter course Acronym e.g HCI for human computer interaction", _assignment.title.split(" ")[0]);

  auth.pid = auth._username+":course-" + course_acron + "-Assignment-"+_assignment.closeTimeString.split(" ")[0].split("-")[2];
  auth.course_acronym = course_acron;
  request(
    "/assignment",
    auth,
    AssignmentUploadCallBack
  );
}

/*
* Feches content from the repository.
*/
var repositoryContentCallBack = function(data){
  console.log("Fetched repository content.");
  RepoResources = data;
  renderRepositoryContent();
}
function getRepositoryContent(){
  request(
    "/repository/content",
    auth,
    repositoryContentCallBack
  );
}

/*-------------------------------------Render views---------------------------------------*/

/*
* Display content from the repository in a folder view structure.
*/
function renderRepositoryContent(){
  var table = $("#RepoResourceTable"); // store the table DOM in a varibale.
  $(".yearClass").hide(); //hide the course path and show
  table.html(""); // delete everything inside the table.

  for(var i = 0; i < RepoResources.length; i++){
    if($("#"+RepoResources[i].label).length == 0) //check if the course folder has not yet been added.
      table.append("<tr id='"+RepoResources[i].label+"' class='repo-course-folder' name='"+RepoResources[i].label+"'><td ><i class='material-icons' >arrow_right folder</i></td><td class='folder'><span>"+RepoResources[i].label+"</span></td><td>"+RepoResources[i].ownerId+"</td><td></td></tr>");//add course folder to the table as a row.
  }

  /*
  * Set the click method for each folder in the table.
  * When each folder is clicked, get the folder's name.
  * Use the name to render course content.
  */
  $(".repo-course-folder").click(function(){
      console.log("Index : "+$(this).attr("name"));
      renderCourse($(this).attr("name"));
  });
}

/*
* This function diplays course content to the screen in table form.
*/
function renderCourse(course_name){
  $(".yearClass").show(); // make the course path visible.
  $(".yearClass").first().html(course_name); // show the course name in the navigation path.
  var table = $("#RepoResourceTable"); // store the table DOM in a varibale.
  table.css("width","750px"); //set the table with.
  table.html(""); // reomove everything in the table.

  table.append("<thead class='Repo-Assignments' ></thead>"); // add the Assignment folder to the table.
  var repo_assignments_classname = ".Repo-Assignments";
  $(repo_assignments_classname).append('<tr class="folder"><td><i class="material-icons" name="'+repo_assignments_classname+'">arrow_drop_down folder_open</i></td><td style="width:650px">Assignments</td><td style="width:200px"></td></tr>');

  table.append("<thead class='Repo-Notes' ></thead>"); // At the Notes folder to the table
  var repo_notes_classname = ".Repo-Notes";
  $(repo_notes_classname).append('<tr class="folder"><td><i class="material-icons" name="'+repo_notes_classname+'">arrow_drop_down folder_open</i></td><td style="width:650px">Notes and Miscellaneous</td><td style="width:200px"></td></tr>');

  table.append("<thead class='Repo-Exams' ></thead>"); // add the exams folder to the table.
  var repo_exams_classname = ".Repo-Exams";
  $(repo_exams_classname).append('<tr class="folder"><td><i class="material-icons" name="'+repo_exams_classname+'">arrow_drop_down folder_open</i></td><td style="width:650px">Exams</td><td style="width:200px"></td></tr>');

  for(var i = 0; i < RepoResources.length; i++){
    console.log("Course Name : "+ RepoResources[i].pid.split("-")[1]);
    if(RepoResources[i].pid.split("-")[1] === course_name){
      if(RepoResources[i].pid.split("-")[2] === "Assignment"){
        renderFolderContent(RepoResources[i], $(repo_assignments_classname));
      }else if(RepoResources[i].pid.split("-")[2] === "Notes"){
        renderFolderContent(RepoResources[i], $(repo_notes_classname));
      }else{
        renderFolderContent(RepoResources[i], $(repo_exams_classname));
      }
    }
  }
  /*
  * Set the click method or the folder. If the folder is closed, open the folder.
  * Else if the folder is open close it.
  */
  $(".folder").find("i").html("arrow_right folder");
  table.find("tr").each(function(){
    $(this).parent().children().slice(1).hide();
  });
  $(".folder").click(function(){
    if($(this).parent().find("i").html() === "arrow_drop_down folder_open"){
      $(this).parent().find("i").first().html("arrow_right folder");
      $(this).parent().children().slice(1).hide();
    }else{
      $(this).find("i").first().html("arrow_drop_down folder_open")
      $(this).parent().children().slice(1).show();
    }
  });
}

/*
* This is a helper function to render folder content.
* It takes an object with a list of reouces, and the folder DOM itself.
* Takes content from the list and diplays them in the folder DOM.
*/
function renderFolderContent(RepoResource, folder){
  var DataStreams = RepoResource.datastreams[0].objectDatastreams.datastream;
  for(var i = 0;  i < DataStreams.length; i++){
      folder.append("<tr class='repo-folder' ><td ><i class='material-icons' >insert_drive_file</i></td><td style='width:650px'><a href='"+getURL(RepoResource.pid,DataStreams[i].dsid)+"'  target='_blank'>"+DataStreams[i].label+"</a></td><td>"+DataStreams[i].mimeType+"</td></tr>");
  }
}

/*
* This method takes in the pid and dsid (data stream id) and uses it
* to return the URL of the file with the given dsid.
*/
function getURL(pid,dsid){
  return "http://localhost:8085/fedora/objects/"+pid+"/datastreams/"+dsid+"/content";
}

/*
* This method displys a list of all sites the user
* belongs to.
*/
function renderVulaSites(){
  $(".course").hide();
  var table = $("#vulaTable");
  foot = $("#vula-button-controlls");
  table.html("");
  for(var i = 0; i < Sites.length; i++){
    table.append("<tr class='site-folder' name='"+Sites[i].id+"'><td ><i class='material-icons' >arrow_right folder</i></td><td class='folder'><span>"+Sites[i].title+"</span></td><td>"+Sites[i].siteOwner.userDisplayName+"</td></tr>");
  }
    $(".site-folder").click(function(){
      $(".course").first().html($(this).find("span").html());
      $(".course").show();
      $("#vula-button-controlls").show();
      requestSiteResources($(this).attr("name"));
    });
}

/*
* When a site is clicked, its id is used to fetch the resources in that site.
* this method renders resources of a site with a given site id.
*/
function renderSiteResources(data){
  this.Resources = data.mycontent;
  this.Assignments = data.assignments;

  var table = $("#vulaTable");
  table.html("");
  table.append(foot);

  console.log("Resources");
 for(var j = 1; j < this.Resources.length; j++){
   var folder_name = this.Resources[j].container.split("/")[4] + "";
   folder_name = folder_name.replace(' ', '-').replace(' ', '-');
   var folder_classname = "." + folder_name;
   if(folder_classname != "."){
     if($(folder_classname).length === 0){
       table.append("<thead class='"+folder_name+"'></thead>");
       $(folder_classname).append('<tr class="folder"><td ><i class="material-icons" name="'+folder_classname+'">arrow_drop_down folder_open</i></td><td >'+folder_name+'</td><td>'+Resources[j].author+'</td><td></td></tr>');
     }
     if(this.Resources[j].type != "collection"){
       $(folder_classname).append('<tr class="file"><td ><input name="'+j+',Resource" type="checkbox"><i class="material-icons" style="text-align:right;">insert_drive_file</i></td><td >'+Resources[j].title+'</td><td>'+this.Resources[j].author+'</td><td><button class="upload-single button blue">upload</button></td></tr>');
     }
   }
  }

  console.log("Assignments");
  table.append("<thead class='Assignments'></thead>");
  var assignments_classname = ".Assignments";
  $(assignments_classname).append('<tr class="folder"><td ><i class="material-icons" name="Assignments">arrow_drop_down folder_open</i></td><td >Site Assignments</td><td></td><td></td></tr>');
  for(var k = 0; k < Assignments.length; k++){
    $(assignments_classname).append('<tr class="file"><td ><input name="'+k+',Assignments" type="checkbox"><i class="material-icons" style="text-align:right;">insert_drive_file</i></td><td >'+Assignments[k].title+'</td><td>Assignment</td><td><button class="upload-single button blue">upload</button></td></tr>');
  }

  $(".folder").find("i").html("arrow_right folder");
    table.find("input").each(function(){
    $(this).parent().parent().parent().children().slice(1).hide();
    });
  $(".folder").click(function(){
    if($(this).parent().find("i").html() === "arrow_drop_down folder_open"){
      $(this).parent().find("i").first().html("arrow_right folder")
      $(this).parent().children().slice(1).hide();
    }else{
      $(this).find("i").first().html("arrow_drop_down folder_open")
      $(this).parent().children().slice(1).show();
    }
  });
  var tmp_course_name = "not set";
  $(".upload-selected").click(function(){

     $("#vulaTable").find("input").each(function(){
       if($(this).is(":checked")){
        //  console.log("ECHO : " + $(this).attr("name").split(",")[2]);
         if($(this).attr("name").split(",")[1] != "Assignments"){

           if(tmp_course_name != Resources[parseInt($(this).attr("name").split(",")[0])].container.split("/")[4].toString()){
              var course_acron = prompt("Please enter course Acronym e.g HCI for human computer interaction", Resources[parseInt($(this).attr("name").split(",")[0])].container.split("/")[4].toString());
              tmp_course_name = course_acron;
           }
           auth.course_acronym = tmp_course_name;
           auth.pid = auth._username + ":course-"+tmp_course_name + "-Notes-"+Resources[parseInt($(this).attr("name").split(",")[0])].modifiedDate.substring(0, 4);
           console.log("Course Name : " + auth.course_acronym);
           console.log("Resource Upload : "+ Resources[parseInt($(this).attr("name").split(",")[0])].container.split("/")[4].toString());
           uploadResource(Resources[parseInt($(this).attr("name").split(",")[0])]);
         }else{
           console.log("Assignment Upload : ");
           uploadAssignment(Assignments[parseInt($(this).attr("name").split(",")[0])]);
         }
       }
     });
   });
   $(".select-all").click(function(){
     if($(this).html() === "select all"){
       $(this).html("unselect all");
       $("#vulaTable").find("input").each(function(){
         $(this).prop('checked', true);
       });
     }else{
       $(this).html("select all");
       $("#vulaTable").find("input").each(function(){
         $(this).prop('checked', false);
       });
     }
   });
   $(".upload-single").click(function(){
     if($(this).parent().parent().find("input").attr("name").split(",")[1] == "Assignments"){
       uploadAssignment(Assignments[parseInt($(this).parent().parent().find("input").attr("name").split(",")[0])]);
     }else{
        var course_acron = prompt("Please enter course Acronym e.g HCI for human computer interaction", Resources[parseInt($(this).parent().parent().find("input").attr("name").split(",")[0])].container.split("/")[4].toString());
        auth.pid = auth._username +":course-"+ course_acron + "-Notes-"+Resources[parseInt($(this).parent().parent().find("input").attr("name").split(",")[0])].modifiedDate.substring(0, 4);
        auth.course_acronym = course_acron;
        uploadResource(Resources[parseInt($(this).parent().parent().find("input").attr("name").split(",")[0])]);
     }
   });
}

$(document).ready(function(){

    $("#loginButton").click(function(){
        Login($(".username").val(),$(".password").val());
    });

    $(".loader").hide();
    $("#FolderView").css("display","none");
    $("#ResourceView").css("display","none");

    $(".path").click(function(){
      if($(this).attr("name") === "sites"){
        renderVulaSites();
      }
      if($(this).attr("name") === "course"){
      }
    });

    $(".path2").click(function(){
      if($(this).attr("name") === "course"){
        renderRepositoryContent();
        $(".yearClass").hide();
      }
      if($(this).attr("name") === "year"){
        $(".yearClass").first().show();
      }
    });
});
