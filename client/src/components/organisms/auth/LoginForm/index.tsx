import React, { useState } from 'react';
import Button from 'components/organisms/common/Button';
import TextInput from 'components/atoms/auth/TextInput';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { useNavigate } from 'react-router-dom';
import { LoginFormContainer } from './style';

function LoginForm() {
	const navigate = useNavigate();
	const [userId, setUserId] = useState('');
	const [userPw, setUserPw] = useState('');
	const [saveLoginState, setSaveLoginState] = useState(false);

	return (
		<LoginFormContainer $saveLoginState={saveLoginState}>
			<div className="login-input">
				<TextInput value={userId} setValue={setUserId} placeholder="아이디" />
				<TextInput value={userPw} setValue={setUserPw} placeholder="비밀번호" />
				<button type="button" className="save-login-state-btn" onClick={() => setSaveLoginState(!saveLoginState)}>
					<Check />
					로그인 상태 유지
				</button>
			</div>
			<div className="btn-group">
				<Button text="로그인" type="Primary" handleClick={() => {}} />
				<div className="menu">
					<button type="button" className="right-bar">
						비밀번호 찾기
					</button>
					<span className="bar">|</span>
					<button type="button" className="right-bar">
						아이디 찾기
					</button>
					<span className="bar">|</span>
					<button type="button" onClick={() => navigate('/auth/register')}>
						회원가입
					</button>
				</div>
			</div>
		</LoginFormContainer>
	);
}

export default LoginForm;
