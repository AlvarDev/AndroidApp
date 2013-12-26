<?php
     require_once "../dao.mysql/MySql_GradoDao.php";
 	
     	$key =$_REQUEST['key'];

     switch ($key) {
		    case 1: //LISTAR
		    	
		    	$objeto=new  MySql_GradoDao();
       			$LISTA= $objeto->listar();
		    	
		    	header('Content-type: application/json');
		    	echo json_encode($LISTA);

		        break;

		    case 2: //ASIGNAR PROFESOR
		    	
		    	$nom = $_REQUEST['nom'];
		    	$ape = $_REQUEST['ape'];
		    	$sueldo = $_REQUEST['sueldo'];
		    	$idNivel = $_REQUEST['idNivel'];
		    	$idGrado = $_REQUEST['idGrado'];
		    	
		    	$objeto=new  MySql_GradoDao();
       			$i= $objeto->agregar($nom, $ape, $sueldo, $idNivel, $idGrado);
		    	if($i==2){       
			         $response["estado"] = "Profesor Asignado";
			       }else{
			         $response["estado"] = "Profesor No Asignado";         
			        }
			    
			    echo json_encode( $response);

		        break;

		    case 3: //ELIMINAR PROFESOR
		    	
		    	$idProfesor = $_REQUEST['idProfesor'];
		    	$idNivel = $_REQUEST['idNivel'];
		    	$idGrado = $_REQUEST['idGrado'];
		        $objeto=new  MySql_GradoDao();
       			$i= $objeto->eliminar($idProfesor, $idNivel, $idGrado);
		    	if($i==2){       
			         $response["estado"] = "Grado Liberado";
			       }else{
			         $response["estado"] = "Grado no liberado";         
			        }

			     echo json_encode( $response);
		        break;
		}

    
?>