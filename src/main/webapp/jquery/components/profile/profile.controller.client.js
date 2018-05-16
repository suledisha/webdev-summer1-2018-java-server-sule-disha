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
    var id
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
            .click(updateUser);

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

    function updateUser() {
        var user = {
            username: $staticUsername.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role: $role.val(),
            phone: $phone.val(),
            email: $email.val(),
            dob: $dob.val()
        };
        userService
            .updateUser(id, user);
        console.log()
    }


})();