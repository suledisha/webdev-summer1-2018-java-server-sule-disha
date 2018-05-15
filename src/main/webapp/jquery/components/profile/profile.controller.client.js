(function() {
    $(init);

    var $staticUsername;
    var $firstName;
    var $lastName;
    var $updateBtn;
    var id
    var userService = new UserServiceClient();

    function init() {
        $staticUsername = $("#staticUsername");
        $firstName = $("#firstName");
        $lastName = $("#lastName");
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
    }

    function updateUser() {
        var user = {
            firstName: $firstName.val(),
            lastName: $lastName.val()
        };
        userService
            .updateUser(id, user);
        console.log()
    }


})();