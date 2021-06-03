$(document).ready(refreshCurrentUserTable());

function refreshCurrentUserTable() {
    $.get(`http://localhost:8080/user/me/`)
        .done((currentUser) => {
            console.log(currentUser)
            $("#currentUserTableBody")
                .empty()
                .append($('<tr>')
                    .append($('<td>').text(currentUser.id))
                    .append($('<td>').text(currentUser.firstName))
                    .append($('<td>').text(currentUser.lastName))
                    .append($('<td>').text(currentUser.age))
                    .append($('<td>').text(currentUser.email))
                    .append($('<td>').text(currentUser.roles.map(item=>item.name)))
                );
        })

}