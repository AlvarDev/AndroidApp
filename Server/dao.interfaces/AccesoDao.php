<?php
     require_once "../dao.mysql/MySql_AccesoDao.php";
 	
     	$key =$_REQUEST['key'];

     switch ($key) {
		    case 1:
		        $usu = $_REQUEST['usuario'];       
      			$cla = $_REQUEST['clave'];
      			$objeto=new  MySql_AccesoDao();
       			$i= $objeto->login($usu,$cla);
       			echo json_encode($i);
		        break;
		    case 2:
		        echo 'no login';
		        break;
		}

    
?>