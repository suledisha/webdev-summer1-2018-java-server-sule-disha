(function() {
    $(init);

    var $staticUsername;
    var $firstName;
    var $lastName;
    var $updateBtn;
    var userService = new UserServiceClient();

    function init() {
        $staticUsername = $("#staticUsername");
        $firstName = $("#firstName");
        $lastName = $("#lastName");

        findUserById(62);
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
})();