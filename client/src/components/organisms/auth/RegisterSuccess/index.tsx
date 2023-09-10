import React from 'react';
import Button from 'components/organisms/common/Button';
import { useNavigate } from 'react-router-dom';
import CompleteLottie from 'components/atoms/lottie/CompleteLottie';
import { RegisterSuccessContainer } from './style';

function RegisterSuccess() {
	const navigate = useNavigate();

	return (
		<RegisterSuccessContainer>
			<div className="message">
				<h2 className="color">보증금도 신한으로 챙겨요!</h2>
				<h2>회원이 되신 것을 환영합니다</h2>
			</div>
			<div className="lottie">
				<CompleteLottie />
			</div>
			<div className="next-btn">
				<Button handleClick={() => navigate('/')} type="Primary" text="로그인 하러가기" />
			</div>
		</RegisterSuccessContainer>
	);
}

export default RegisterSuccess;
