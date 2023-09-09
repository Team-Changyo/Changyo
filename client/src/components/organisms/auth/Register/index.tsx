import React, { Dispatch, SetStateAction, useState } from 'react';
import Button from 'components/organisms/common/Button';
import { RegisterContainer } from './style';
import RegisterForm from '../RegisterForm';

interface IRegisterProps {
	setStep: Dispatch<SetStateAction<number>>;
}
function Register({ setStep }: IRegisterProps) {
	const [isDone, setIsDone] = useState(false);

	const handleClick = () => {
		if (isDone) {
			setStep(2);
		} else {
			// TODO : 토스트 메시지로 변경할 것.
			console.log('모두 체크해라');
		}
	};

	return (
		<RegisterContainer>
			<RegisterForm setIsDone={setIsDone} />
			{/* 회원가입 완료 */}
			<div className="next-btn">
				<Button handleClick={handleClick} type={isDone ? 'Primary' : 'Normal'} text="회원가입 완료" />
			</div>
		</RegisterContainer>
	);
}

export default Register;
