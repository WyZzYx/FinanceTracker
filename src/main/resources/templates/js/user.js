document.addEventListener("DOMContentLoaded", async () => {
    try {
        const token = localStorage.getItem('jwtToken');

        if (!token) {
            alert("You are not logged in. Redirecting to login page.");
            window.location.href = '/auth/login'; // Redirect to login if token is missing
            return;
        }

        const response = await fetch('/user/data', {
            method: 'GET',
            headers: {
                'Authorization': 'Bearer ' + token
            }
        });

        if (response.ok) {
            const data = await response.json();
            document.getElementById("userData").innerText = JSON.stringify(data, null, 2);
        } else if (response.status === 401) {
            alert("Unauthorized. Please log in again.");
            localStorage.removeItem('jwtToken'); // Remove token

            window.location.href = '/auth/login'; // Redirect to login page
        } else {
            console.error("Failed to fetch user data:", response.status);
        }
    } catch (error) {
        console.error("Error fetching user data:", error);
    }
});