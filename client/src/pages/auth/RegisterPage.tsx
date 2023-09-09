import Register from 'components/organisms/auth/Register';
import RegisterSuccess from 'components/organisms/auth/RegisterSuccess';
import TermsOfService from 'components/organisms/auth/TermsOfService';
import SubTabNavbar from 'components/organisms/common/SubTabNavbar';
import RegisterPageLayout from 'layouts/page/auth/RegisterPageLayout';
import React, { useEffect, useState } from 'react';

function RegisterPage() {
	const [step, setStep] = useState(0);
	const [navText, setNavText] = useState('');
	const [stepView, setStepView] = useState(<div />);

	useEffect(() => {
		switch (step) {
			case 0: {
				setNavText('약관 동의');
				setStepView(<TermsOfService setStep={setStep} />);
				break;
			}
			case 1: {
				setNavText('회원가입');
				setStepView(<Register setStep={setStep} />);
				break;
			}
			case 2: {
				setNavText('회원가입 완료');
				setStepView(<RegisterSuccess />);
				break;
			}
			default: {
				setStepView(<div>error</div>);
				break;
			}
		}
	}, [step]);

	return (
		<RegisterPageLayout
			Navbar={<SubTabNavbar text={navText} type={step < 2 ? 'back' : 'close'} closePath="/" />}
			StepView={stepView}
		/>
	);
}

export default RegisterPage;
