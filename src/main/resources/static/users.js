$(document).ready(refreshUsersTable());

function refreshUsersTable() {
    $.get(`http://localhost:8080/Admin/me`)
        .done((currentUser) => {
            $('#current_user_roles').empty().text(currentUser.roles.map(item => item.name));
            fillInUsersTable(currentUser.roles.some(item => item.name === 'ADMIN'));
        })
}

function refreshCurrentUserTable() {
    $.get(`http://localhost:8080/Admin/me`)
        .done((currentUser) => {

            $('#modalEdit').modal('hide');
            $("#currentUserTableBody")
                .empty()
                .append($('<tr>')
                    .append($('<td>').text(currentUser.id))
                    .append($('<td>').text(currentUser.firstName))
                    .append($('<td>').text(currentUser.lastName))
                    .append($('<td>').text(currentUser.age))
                    .append($('<td>').text(currentUser.email))
                    .append($('<td>').text(currentUser.roles.map(item => item.name)))
                );
        })
}

function fillInUsersTable(isAdmin) {
    $.get(`http://localhost:8080/Admin`)
        .done((usersList) => {
            $('#modalEdit').modal('hide');
            $(document).ready(() => $("#userTablePage").click());
            $(document).ready(() => $("#usersTableBody").empty());
            $(document).ready(() => {
                for (let i = 0; i < usersList.length; i++) {
                    const user = usersList[i];
                    if (isAdmin === true) {
                        $("#usersTableBody")
                            .append($('<tr>')
                                .append($('<td>').text(user.id))
                                .append($('<td>').text(user.firstName))
                                .append($('<td>').text(user.lastName))
                                .append($('<td>').text(user.age))
                                .append($('<td>').text(user.email))
                                .append($('<td>').text(user.roles.map(item=>item.name)))
                                .append($('<td>').html("<input onclick=\"openEditUserModal(" + user.id + ")\" type=\"button\" class=\"btn btn-primary\" value=\"Edit\">"))
                                .append($('<td>').html("<input onclick=\"deleteUser(" + user.id + ")\" type=\"button\" class=\"btn btn-danger\"value=\"Delete\">"))
                            );
                    } else {
                        $("#usersTableBody")
                            .append($('<tr>')
                                .append($('<td>').text(user.id))
                                .append($('<td>').text(user.firstName))
                                .append($('<td>').text(user.lastName))
                                .append($('<td>').text(user.age))
                                .append($('<td>').text(user.email))
                                .append($('<td>').text(user.roles.map(item=>item.name)))
                            );
                    }
                }
            });
        });
}
function createUser() {
    let user = {
        "firstName": $('#firstNameCreate').val(),
        "lastName": $('#lastNameCreate').val(),
        "age": $('#ageCreate').val(),
        "email": $('#emailCreate').val(),
        "passWord": $('#passwordCreate').val(),
        "roles": $('#rolesCreate').val()
    };
    $.ajax({
        url: 'http://localhost:8080/Admin',
        type: 'post',
        data: JSON.stringify(user),
        headers: {
            'x-auth-token': localStorage.accessToken,
            "Content-Type": "application/json"
        },
        dataType: 'json',
        success: refreshUsersTable,
        error: (data) => showError(data)
    })
}

function deleteUser(userId) {
    $.ajax({
        url: `http://localhost:8080/Admin/${userId}/delete`,
        type: 'delete',
        headers: {
            'x-auth-token': localStorage.accessToken
        },
        success: refreshUsersTable,
        error: (data) => showError(data)
    })
}

function openEditUserModal(userId) {

    $.ajax({
        url: `http://localhost:8080/Admin/${userId}`,
        type: 'get',
        headers: {
            'x-auth-token': localStorage.accessToken,
        },
        success: (user) => {
            console.log("it is all users table: " +user.firstName+" roles id: " +user.roles.map(item=>item.id))
            sendEditRequest(user);
        },
        error: (data) => showError(data)
    })
}

function sendEditRequest(user) {
    $('#idE').val(user.id)
    $('#usernameE').val(user.firstName);
    $('#lastnameE').val(user.lastName);
    $('#ageE').val(user.age);
    $('#emailE').val(user.email);
    $('#passwordE').val(user.password);

    $('#modalEdit').modal('show');

    $('#edit_button').off("click");

    $('#edit_button').click(() => {
        let userE = {
            "id" : user.id,
            "firstName": $('#usernameE').val(),
            "lastName": $('#lastnameE').val(),
            "age": $('#ageE').val(),
            "email": $('#emailE').val(),
            "passWord": $('#passwordE').val(),
            "roles": $('#rolesSelect').val()
        };
        console.log("this is edited user: ", JSON.stringify(userE))
        $.ajax({
            url: `http://localhost:8080/Admin/update`,
            type: 'put',
            data: JSON.stringify(userE),
            headers: {
                'x-auth-token': localStorage.accessToken,
                "Content-Type": "application/json"
            },
            dataType: 'json',
            success: refreshUsersTable,
            error: (data) => showError(data)
        })
    });

}

function showError(message) {
    alert(message.responseText);
}
