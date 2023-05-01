let index = {
    init: function() {
        $(".btn-join").on("click", () => {
            this.join();
        })
    },
    join: function() {
        $.ajax({
            type: "POST",
            url: "/member/join",
            data: $("#memberForm").serialize()
        }).done(function(res) {
            if(res.status === 200) {
                show("is-success", res.data.userName + "님 환영합니다.");
                document.querySelector('#modal-login').classList.remove('is-active');
            } else {
                show("is-danger","회원가입을 실패했습니다.");
            }
        }).fail(function(err) {
            show("is-danger", err);
        })
    },
}

index.init();

const show = (status, msg) => {
    bulmaToast.toast({
        message: msg,
        type: status,
        position: "center",
        dismissible: true,
        duration: 4000,
        pauseOnHover: true,
        animate: { in: 'fadeIn', out: 'fadeOut' }
    })
}

document.addEventListener('DOMContentLoaded', () => {
    // Functions to open and close a modal
    function openModal($el) {
        $el.classList.add('is-active');
    }

    function closeModal($el) {
        $el.classList.remove('is-active');
    }

    function closeAllModals() {
        (document.querySelectorAll('.modal') || []).forEach(($modal) => {
            closeModal($modal);
        });
    }

    // Add a click event on buttons to open a specific modal
    (document.querySelectorAll('.js-modal-trigger') || []).forEach(($trigger) => {
        const modal = $trigger.dataset.target;
        const $target = document.getElementById(modal);

        $trigger.addEventListener('click', () => {
            openModal($target);
        });
    });

    // Add a click event on various child elements to close the parent modal
    /*(document.querySelectorAll('.modal-background, .modal-close, .modal-card-head .delete, .modal-card-foot .button') || []).forEach(($close) => {*/
    (document.querySelectorAll('.modal-close, .modal-card-head .delete, .modal-card-foot .button-close') || []).forEach(($close) => {
        const $target = $close.closest('.modal');

        $close.addEventListener('click', () => {
            closeModal($target);
        });
    });

    // Add a keyboard event to close all modals
    document.addEventListener('keydown', (event) => {
        const e = event || window.event;

        if (e.keyCode === 27) { // Escape key
            closeAllModals();
        }
    });
});