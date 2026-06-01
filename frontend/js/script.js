const API_BASE = 'https://college-club-production.up.railway.app';

// ===== CONTACT FORM =====
function sendMessage() {
    const name = document.getElementById('name').value;
    const email = document.getElementById('email').value;
    const subject = document.getElementById('subject').value;
    const message = document.getElementById('message').value;

    if (name && email && message) {
        fetch(API_BASE + '/api/contact', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ name, email, subject, message })
        })
        .then(res => res.json())
        .then(() => alert(`Thank you ${name}! Your message has been sent!`))
        .catch(() => alert('Error sending message. Please try again.'));
    } else {
        alert('Please fill in all required fields!');
    }
}

// ===== LOAD MEMBERS FROM BACKEND =====
function loadMembers() {
    fetch(API_BASE + '/api/members')
        .then(res => res.json())
        .then(members => {
            const container = document.getElementById('members-container');
            if (!container) return;
            container.innerHTML = '';
            if (members.length === 0) {
                container.innerHTML = '<p>No members found.</p>';
                return;
            }
            members.forEach(member => {
                container.innerHTML += `
                    <div class="member-card">
                        <h3>${member.name}</h3>
                        <p><strong>Role:</strong> ${member.role}</p>
                        <p><strong>Email:</strong> ${member.email}</p>
                        <p><strong>Phone:</strong> ${member.phone}</p>
                    </div>
                `;
            });
        })
        .catch(err => console.error('Error loading members:', err));
}

// ===== LOAD EVENTS FROM BACKEND =====
function loadEvents() {
    fetch(API_BASE + '/api/events')
        .then(res => res.json())
        .then(events => {
            const container = document.getElementById('events-container');
            if (!container) return;
            container.innerHTML = '';
            if (events.length === 0) {
                container.innerHTML = '<p>No events found.</p>';
                return;
            }
            events.forEach(event => {
                container.innerHTML += `
                    <div class="event-card">
                        <h3>${event.title}</h3>
                        <p>${event.description}</p>
                        <p><strong>Date:</strong> ${event.eventDate}</p>
                    </div>
                `;
            });
        })
        .catch(err => console.error('Error loading events:', err));
}

// ===== AUTO LOAD ON PAGE =====
window.onload = function () {
    loadMembers();
    loadEvents();
};