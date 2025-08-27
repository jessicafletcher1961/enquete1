package fr.colline.monatis.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import fr.colline.monatis.controller.dto.ErreurDto;

@ControllerAdvice
public class MonatisExceptionHandler {

	@ExceptionHandler(ControllerException.class)
	public final ResponseEntity<ErreurDto> handleControllerExceptions(
			ControllerException ex) {

		ErreurDto erreurDto = construitErreurDto(ex);
		
		return new ResponseEntity<>(erreurDto, ex.getStatus());
	}

	private ErreurDto construitErreurDto(Throwable exception) {
		
		ErreurDto erreurDto = new ErreurDto();

		if ( ControllerException.class.isAssignableFrom(exception.getClass()) ) {
			ControllerException ex = (ControllerException) exception;
			erreurDto.code = ex.getCode();
			erreurDto.libelle = ex.getMessage();
			erreurDto.typeLibelle = ex.getType().getLibelle();
			erreurDto.status = ex.getStatus();
		}
		else if ( MonatisExceptionInterface.class.isAssignableFrom(exception.getClass())){
			MonatisExceptionInterface ex = (MonatisExceptionInterface) exception;
			erreurDto.code = ex.getCode();
			erreurDto.libelle = ex.getMessage();
			erreurDto.typeLibelle = ex.getType().getLibelle();
			erreurDto.status = null;
		}
		else {
			erreurDto.code = "TEC-XXXX";
			erreurDto.libelle = exception.getMessage();
			erreurDto.typeLibelle = "Erreur technique non gérée";
			erreurDto.status = null;
		}

		erreurDto.cause = null;
		if ( exception.getCause() != null ) {
			erreurDto.cause = construitErreurDto(exception.getCause());
		}
		
		return erreurDto;
	}
}
