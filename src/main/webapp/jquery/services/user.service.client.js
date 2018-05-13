function UserServiceClient() {
    this.findAllUsers = findAllUsers;
    this.createUser = createUser;
    this.url =
        'http://localhost:8080/api/user';
    this.login =
        'http://localhost:8080/api/login';
    var self = this;

    function findAllUsers() {
        return fetch(self.url)
            .then(function (response) {
                return response.json();
            });
    }

    function createUser(user) {
        return fetch(self.url, {
            method: 'post',
            body: JSON.stringify(user),
            headers: {
                'content-type': 'application/json'
            }
        });
    }
}
