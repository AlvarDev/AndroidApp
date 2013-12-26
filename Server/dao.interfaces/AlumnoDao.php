<?php
     require_once "../dao.mysql/MySql_AlumnoDao.php";
 	
     	$key =$_REQUEST['key'];

     switch ($key) {
		    case 1: //AGREGAR  
		    	
		    	$nom = $_REQUEST['nom'];
		    	$ape = $_REQUEST['ape'];
		    	$fechNac = $_REQUEST['fechNac'];
		    	$idNivel = $_REQUEST['idNivel'];
		    	$idGrado = $_REQUEST['idGrado'];
		    	
		    	$objeto=new  MySql_AlumnoDao();
       			$i= $objeto->agregar($nom, $ape, $fechNac, $idNivel, $idGrado);
		    	if($i==1){       
			         $response["estado"] = "Alumno Matriculado";
			       }else{
			         $response["estado"] = "Alumno No Matriculado";         
			        }
			    
			    echo json_encode( $response);

		        break;
		    case 2: //EDITAR

		        $idAlumno = $_REQUEST['idAlumno'];
		        $nom = $_REQUEST['nom'];
		    	$ape = $_REQUEST['ape'];
		    	$fechNac = $_REQUEST['fechNac'];
		    	$idNivel = $_REQUEST['idNivel'];
		    	$idGrado = $_REQUEST['idGrado'];
		    	
		    	$objeto=new  MySql_AlumnoDao();
       			$i= $objeto->actualizar($idAlumno, $nom, $ape, $fechNac, $idNivel, $idGrado);
		    	if($i==1){       
			         $response["estado"] = "Datos Actualizados";
			       }else{
			         $response["estado"] = "Datos No Actualizados";         
			        }
			    
			    echo json_encode( $response);

		        break;
		    case 3: //Eliminar
		        $idAlumno = $_REQUEST['idAlumno'];
		        $objeto=new  MySql_AlumnoDao();
       			$i= $objeto->eliminar($idAlumno);
		    	if($i==1){       
			         $response["estado"] = "Alumno Eliminado";
			       }else{
			         $response["estado"] = "Alumno Eliminado";         
			        }

			     echo json_encode( $response);
		        break;
		    case 4: //LISTAR TODO

		    	$idNivel = $_REQUEST['idNivel'];
		    	$idGrado = $_REQUEST['idGrado'];
		    	
		    	$objeto=new  MySql_AlumnoDao();
		    	$LISTA=$objeto->listar($idNivel, $idGrado);

		    	header('Content-type: application/json');
		    	echo json_encode($LISTA);
		        break;
		    case 5: //Litar
		        
		        $idAlumno = $_REQUEST['idAlumno'];
		        $objeto=new  MySql_AlumnoDao();
		    	$LISTA=$objeto->listarAlumno($idAlumno);
		    	header('Content-type: application/json');
		    	echo json_encode($LISTA);
		        break;
		}

    
?>