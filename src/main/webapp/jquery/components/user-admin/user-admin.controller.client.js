//IIFE
(function () {

    $(init);

    var $username;
    var $password;
    var $firstName;
    var $lastName;
    var $role;


    var tbody;
    var template;
    var id;
    var userService = new UserServiceClient()

    function init() {

       $username = $('#usernameFld')
       $password = $('#passwordFld')
       $firstName = $('#firstNameFld')
       $lastName = $('#lastNameFld')
       $role = $('#roleFld')


        tbody = $('.wbdv-tbody');
        template = $('.wbdv-template');

        $('#createUser').click(createUser);
        $('#update').click(updateUser);

        findAllUsers();
    }

    function findAllUsers() {
        userService
            .findAllUsers()
            .then(renderUsers);
    }
    function renderUsers(users) {
        tbody.empty();
        for(var i=0; i<users.length; i++) {
            var user = users[i];
            var clone = template.clone();

            clone.attr('id', user.id);

            clone.find('.wbdv-remove').click(deleteUser);
            clone.find('.wbdv-edit').click(selectUser);

            clone.find('.wbdv-username')
                .html(user.username);
            clone.find('.wbdv-first-name')
                .html(user.firstName);
            clone.find('.wbdv-last-name')
                .html(user.lastName);
            clone.find('.wbdv-role')
                .html(user.role);
            tbody.append(clone);
        }
    }

    function createUser() {
        console.log('createUser');
        console.log($username.val());
        console.log($password.val());
        console.log($firstName.val());
        console.log($lastName.val());
        console.log($role.val());


        var user = {
            username: $username.val(),
            password: $password.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role:   $role.val()
        };

        userService
            .createUser(user)
            .then(findAllUsers);
    }

    function deleteUser(event) {
        console.log('deleteUser');
        var deleteBtn = $(event.currentTarget);
        var userId = deleteBtn
            .parent()
            .parent()
            .parent()
            .attr('id');

        userService
            .deleteUser(userId)
            .then(findAllUsers);
    }

    function selectUser(event) {
        console.log('selectUser');
        var selectBtn = $(event.currentTarget);
        var userId = selectBtn
            .parent()
            .parent()
            .parent()
            .attr('id');
        id=userId;
        findUserById(userId)
    }

    function findUserById(userId) {
        userService
            .findUserById(userId)
            .then(renderUser);
    }

    function renderUser(user) {
        console.log(user);
        $username.val(user.username);
        $firstName.val(user.firstName);
        $lastName.val(user.lastName);
    }

    function updateUser(){
        console.log("Update User");

        var newuser = {
            username: $username.val(),
            password: $password.val(),
            firstName: $firstName.val(),
            lastName: $lastName.val(),
            role:   $role.val()
        };
        console.log(newuser);
        console.log(id);
        userService
            .updateUser(id,newuser)
            .then(findAllUsers);


    }


})();