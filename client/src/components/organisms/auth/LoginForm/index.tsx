import React, { useState } from 'react';
import Button from 'components/organisms/common/Button';
import TextInput from 'components/atoms/auth/TextInput';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { useNavigate } from 'react-router-dom';
import { loginApi } from 'utils/apis/member';
import { LoginApiBody } from 'types/api';
import { LoginFormContainer } from './style';

function LoginForm() {
	const navigate = useNavigate();
	const [memberId, setMemberId] = useState('');
	const [memberPw, setMemberPw] = useState('');
	const [saveLoginState, setSaveLoginState] = useState(false);

	const login = async () => {
		try {
			const body: LoginApiBody = {
				loginId: memberId,
				password: memberPw,
			};
			const response = await loginApi(body);
			console.log(response);
		} catch (error) {
			console.error(error);
		}
	};
	return (
		<LoginFormContainer $saveLoginState={saveLoginState}>
			<div className="login-input">
				<TextInput value={memberId} setValue={setMemberId} placeholder="아이디" />
				<TextInput value={memberPw} setValue={setMemberPw} placeholder="비밀번호" />
				<button type="button" className="save-login-state-btn" onClick={() => setSaveLoginState(!saveLoginState)}>
					<Check />
					로그인 상태 유지
				</button>
			</div>
			<div className="btn-group">
				<Button text="로그인" type="Primary" handleClick={login} />
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
