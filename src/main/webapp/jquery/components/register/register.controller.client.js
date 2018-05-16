(function () {

    var $username;
    var $password;
    var $verifyPassword;
    //var $registerBtn;

    var userService = new UserServiceClient()
    $(init);
    function init() {

        $username = $('#usernameFld')
        $password = $('#passwordFld')
        $verifyPassword = $('#verifyPasswordFld')

        $('#registerBtn').click(register);
    }
    function register() {

        console.log("In register")
        console.log($username.val());
        console.log($password.val());
        console.log($verifyPassword.val());

        var user = {
            username: $username.val(),
            password: $password.val()
        };
        findUserByUsername(user)
        //userService.register(user)

    }

    function findUserByUsername(user){
        console.log("In findUserByUserName")
        userService.findUserByUsername(user).then(function (response) { console.log(response);
        if(response.status===409)
        {
            console.log("REGISTER");
            userService.createUser(user).then(function (response) {
                console.log(response.id);
                window.location.href = "/jquery/components/profile/profile.template.client.html?userId=" + response.id })
        }
        else
        {
            console.log("DONT REGSITER");
        }})

    }
})();