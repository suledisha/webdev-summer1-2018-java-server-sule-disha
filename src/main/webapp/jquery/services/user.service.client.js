function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.deleteUser = deleteUser;
    this.findUserById = findUserById;
    this.updateUser = updateUser;
    this.updateProfile = updateProfile;
    this.login = login;
    this.success = success;
    this.error = error;
    this.findUserByUsername = findUserByUsername;
    //this.findUserByCredentials=findUserByCredentials;


    this.url =
        '/api/user';
    this.login_url =
        '/api/login';
    this.register_url =
        '/api/register';
    this.profile_url =
        '/api/profile';
    var self = this;

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        console.log(user)
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            return response.json();
        });
    }

    function deleteUser(userId) {
        return fetch(self.url + '/' + userId, {
            method: 'delete'
        })
    }

    function login(username, password) {
        return fetch(self.login_url, {
            method: 'post',
            body: JSON.stringify({username: username, password: password}),
            headers: {
                'content-type': 'application/json'
            }
        }).then(function (response) {
            if (response.status === 200) {
                return response.json();
            }
            else {
                alert("Invalid username/password!")
            }

        });
    }

    function updateUser(userId, user) {
        return fetch(self.url + '/' + userId, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(success, error);
    }

    function updateProfile(user) {
        return fetch(self.profile_url, {
            method: 'put',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        }).then(success, error);
    }

    function success(response) {
        console.log("In Success")
        if (response.status === 409)
            alert('Unable to update');
        else
            alert("Success");
    }

    function error(response) {
        console.log("In Error")
        alert('Unable to update');
    }

    function findUserById(userId) {
        return fetch(self.url + '/' + userId)
            .then(function (response) {
                return response.json();
            });
    }


    function findUserByUsername(username) {
        console.log("in user.service.client");
        console.log(username);
        return fetch(self.register_url, {
            method: 'post',
            body: JSON.stringify({username: username}),
            headers: {
                'content-type': 'application/json'
            }
        })
        //.then(register_success);
    }

    /*
    function findUserByCredentials(user){
        console.log("in user.service.client");
        console.log(user);
        return fetch(self.login_url);
    }**/


}
