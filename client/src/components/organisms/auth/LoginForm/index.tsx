import React, { useState } from 'react';
import Button from 'components/organisms/common/Button';
import TextInput from 'components/atoms/auth/TextInput';
import { ReactComponent as Check } from 'assets/icons/check.svg';
import { useNavigate } from 'react-router-dom';
import { findMemberInfo, loginApi } from 'utils/apis/auth';
import { toast } from 'react-hot-toast';
import { useRecoilState } from 'recoil';
import { memberInfoState } from 'store/member';
import { LoginFormContainer } from './style';

function LoginForm() {
	const navigate = useNavigate();
	const [loginId, setLoginId] = useState('');
	const [password, setPassword] = useState('');
	const [saveLoginState, setSaveLoginState] = useState(false);
	const [, setMemberInfo] = useRecoilState(memberInfoState);

	const login = async () => {
		try {
			const body = {
				loginId,
				password,
			};

			const loginResponse = await loginApi(body);

			if (loginResponse.status === 200) {
				localStorage.setItem('accessToken', loginResponse.data.data.accessToken);
				localStorage.setItem('refreshToken', loginResponse.data.data.refreshToken);

				try {
					const response = await findMemberInfo();

					if (response.status === 200) {
						setMemberInfo(response.data.data);
						toast.success('로그인 되었습니다.');
						navigate('/');
					}
				} catch (error) {
					toast.error('회원정보 로드에 실패했습니다. 잠시 후 다시 시도하세요.');
				}
			}
		} catch (error) {
			toast.error('아이디와 비밀번호를 확인하세요');
		}
	};
	return (
		<LoginFormContainer $saveLoginState={saveLoginState}>
			<div className="login-input">
				<TextInput value={loginId} setValue={setLoginId} label="아이디" placeholder="" type="text" />
				<TextInput value={password} setValue={setPassword} label="비밀번호" placeholder="" type="password" />
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
