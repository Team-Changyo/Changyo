import React, { Dispatch, SetStateAction } from 'react';
import { RegisterContainer } from './style';
import RegisterForm from '../RegisterForm';

interface IRegisterProps {
	setStep: Dispatch<SetStateAction<number>>;
}
function Register({ setStep }: IRegisterProps) {
	return (
		<RegisterContainer>
			<RegisterForm setStep={setStep} />
		</RegisterContainer>
	);
}

export default Register;
