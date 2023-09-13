import React, { Dispatch, SetStateAction, useEffect, useState } from 'react';
import TextInput from 'components/atoms/auth/TextInput';
import Button from 'components/organisms/common/Button';
import { joinApi } from 'utils/apis/auth';
import CheckText from 'components/atoms/common/CheckText';
import { toast } from 'react-hot-toast';
import { RegisterFormContainer } from './style';

interface IRegisterFormProps {
	setStep: Dispatch<SetStateAction<number>>;
}
function RegisterForm({ setStep }: IRegisterFormProps) {
	const [isDone, setIsDone] = useState(false);
	const [loginId, setLoginId] = useState('');
	const [password, setPassword] = useState('');
	const [passwordConfirm, setPasswordConfirm] = useState('');
	const [name, setName] = useState('');
	const [phoneNumber, setPhoneNumber] = useState('');
	const [certNumber, setCertNumber] = useState('');
	const [isStore, setIsStore] = useState(false);

	// 휴대폰 인증 관련
	const [isCertified, setIsCertified] = useState(false);
	const [activeCert, setActiveCert] = useState(false);

	// 회원가입 완료 버튼
	const join = async () => {
		if (isDone) {
			const body = {
				loginId,
				password,
				name,
				phoneNumber,
				role: isStore ? 'STORE' : 'MEMBER',
			};
			// 로그인
			try {
				const response = await joinApi(body);

				if (response.status === 201) {
					console.log('회원가입 :: ', response);
					toast.success('회원가입에 성공했습니다!');
					setStep(2);
				}
			} catch (error) {
				console.error(error);
			}
		} else {
			toast.error('항목을 모두 입력해주세요.');
		}
	};

	useEffect(() => {
		if (password && passwordConfirm && name && phoneNumber && isCertified) {
			setIsDone(true);
		} else {
			setIsDone(false);
		}
	}, [loginId, password, passwordConfirm, name, phoneNumber, isCertified]);

	return (
		<RegisterFormContainer $isStore={isStore}>
			{/* 로그인 정보(아이디, 비밀번호) */}
			<div className="login-info">
				<TextInput type="text" label="아이디" placeholder="" value={loginId} setValue={setLoginId} />
				<TextInput type="password" label="비밀번호" placeholder="" value={password} setValue={setPassword} />
				<TextInput
					type="password"
					label="비밀번호 확인"
					placeholder=""
					value={passwordConfirm}
					setValue={setPasswordConfirm}
				/>
			</div>

			{/* 사용자 정보(이름, 전화번호) */}
			<div className="user-info">
				<TextInput label="이름" type="text" placeholder="" value={name} setValue={setName} />
				<TextInput
					label="전화번호"
					type="tel"
					placeholder="010-0000-0000"
					value={phoneNumber}
					setValue={setPhoneNumber}
				/>

				{isCertified ? (
					// 휴대폰 인증 완료
					<Button handleClick={() => {}} text="휴대폰 인증 완료" type="Normal" />
				) : (
					// 휴대폰 인증 전
					<>
						{activeCert ? (
							// 휴대폰 인증번호 전송 완료
							<>
								<TextInput type="number" label="인증번호" value={certNumber} setValue={setCertNumber} placeholder=" " />
								<Button
									handleClick={
										certNumber.length >= 6
											? () => {
													setActiveCert(false);
													setIsCertified(true);
													toast.success('휴대폰이 인증되었습니다!');
											  }
											: () => {}
									}
									text="인증번호 확인"
									type={certNumber.length >= 6 ? 'Primary' : 'Normal'}
								/>
							</>
						) : (
							// 휴대폰 인증번호 전송 전
							<Button
								handleClick={
									phoneNumber.length >= 11
										? () => {
												setActiveCert(true);
												toast.success('인증번호가 전송되었습니다');
										  }
										: () => {}
								}
								text="휴대폰 인증번호 전송"
								type={phoneNumber.length >= 11 ? 'Primary' : 'Normal'}
							/>
						)}
					</>
				)}
			</div>
			{/* 회원/점주(role) 확인 */}
			<div className="check-role">
				<CheckText value={isStore} setValue={setIsStore} text="저는 사업체를 운영하고 있어요 !" />
			</div>

			{/* 회원가입 완료 */}
			<div className="next-btn">
				<Button handleClick={join} type={isDone ? 'Primary' : 'Normal'} text="회원가입 완료" />
			</div>
		</RegisterFormContainer>
	);
}

export default RegisterForm;
