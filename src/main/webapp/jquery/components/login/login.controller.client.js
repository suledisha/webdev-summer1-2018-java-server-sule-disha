(function () {

    var $username;
    var $password;

    var userService = new UserServiceClient();
    $(init);
    function init() {

        $username = $('#usernameFld');
        $password = $('#passwordFld');

        $('#loginBtn').click(login);
    }
    function login() {

        console.log("In Login");
        console.log($username.val());
        console.log($password.val());

        var user = {
            username: $username.val(),
            password: $password.val()
        };
        userService.login(user.username,user.password)
            .then(function (response) {
                console.log(response);
                window.location.href = "/jquery/components/profile/profile.template.client.html?userId=" + response.id

            })}

})();