(function() {
    $(init);

    var $staticUsername;
    var $firstName;
    var $lastName;
    var $role;
    var $phone;
    var $dob;
    var $email;

    var $updateBtn;
    var $logoutBtn;
    var id;
    var userService = new UserServiceClient();

    function init() {
        $staticUsername = $("#staticUsername");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
        $role=  $("#role");
        $phone = $("#phone");
        $dob = $("#dob");
        $email = $("#email");
        $updateBtn = $("#updateBtn")
            .click(updateProfile);
        $logoutBtn = $("#logoutBtn")
            .click(logout);

        id = getUrlVars()[1]
        findUserById(id);
    }

    function getUrlVars() {
        var hash;
        var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1);
        hash = hashes.split('=');
        console.log(hash)
        return hash;
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $staticUsername.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
        $role.val(user.role);
        $phone.val(user.phone);
        $dob.val(user.dob);
        $email.val(user.email);

    }

    function updateProfile() {
        var user = {
            id:id,
            username: $staticUsername.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val(),
            phone: $phone.val(),
            email: $email.val(),
            dob: $dob.val()
        };
        userService
            .updateProfile(user);
        console.log(id);
        console.log(user);
    }

    function logout(){
        console.log("Logging Out");
        window.location.href = "/jquery/components/login/login.template.client.html"
    }


})();