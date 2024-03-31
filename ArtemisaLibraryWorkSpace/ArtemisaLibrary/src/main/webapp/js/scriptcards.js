document.addEventListener('DOMContentLoaded', () => {
    const cards = document.querySelectorAll('.cardu');
    const sensitivity = 0.2; // Sensibilidad del efecto parallax

    cards.forEach(card => {
        const cardInner = card.querySelector('.cardu-inner');

        card.addEventListener('mouseenter', () => {
            card.addEventListener('mousemove', handleMouseMove);
        });

        card.addEventListener('mouseleave', () => {
            card.removeEventListener('mousemove', handleMouseMove);
            cardInner.style.transform = 'rotateX(0deg) rotateY(0deg)';
        });

        function handleMouseMove(e) {
            const cardRect = card.getBoundingClientRect();
            const mouseX = (cardRect.width / 2 - (e.clientX - cardRect.left)) * sensitivity;
            const mouseY = (cardRect.height / 2 - (e.clientY - cardRect.top)) * sensitivity;
            cardInner.style.transform = `rotateX(${mouseY}deg) rotateY(${mouseX}deg)`;
        }
    });
});