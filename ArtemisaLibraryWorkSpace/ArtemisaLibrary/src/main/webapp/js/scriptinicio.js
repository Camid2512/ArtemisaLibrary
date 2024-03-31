(function() {
      var loginMsg = document.querySelector('.loginMsg');
      var login = document.querySelector('.login');
      var signupMsg = document.querySelector('.signupMsg');
      var signup = document.querySelector('.signup');
      var frontbox = document.querySelector('.frontbox');

      document.getElementById('switch1').addEventListener('click', function() {
          loginMsg.classList.toggle("visibility");
          frontbox.classList.add("moving");
          signupMsg.classList.toggle("visibility");

          signup.classList.toggle('hide');
          login.classList.toggle('hide');
      });

      document.getElementById('switch2').addEventListener('click', function() {
          loginMsg.classList.toggle("visibility");
          frontbox.classList.remove("moving");
          signupMsg.classList.toggle("visibility");

          signup.classList.toggle('hide');
          login.classList.toggle('hide');
      });

      document.getElementById('switch1').click();

  })();
  //]]>