import React, { useRef, KeyboardEvent, Dispatch, SetStateAction, useEffect, useState } from 'react';
import { CertCodeInputContainer } from './style';

interface ICertCodeInputProps {
	setCertCode: Dispatch<SetStateAction<string>>;
}
function CertCodeInput({ setCertCode }: ICertCodeInputProps) {
	const inputRefs = [
		useRef<HTMLInputElement | null>(null),
		useRef<HTMLInputElement | null>(null),
		useRef<HTMLInputElement | null>(null),
		useRef<HTMLInputElement | null>(null),
	];

	const [first, setFirst] = useState('');
	const [second, setSecond] = useState('');
	const [third, setThird] = useState('');
	const [fourth, setFourth] = useState('');
	const setCodeFuncArr = [setFirst, setSecond, setThird, setFourth];

	const handleKeyUp = (event: KeyboardEvent<HTMLInputElement>, index: number) => {
		if (event.currentTarget.value.length === event.currentTarget.maxLength) {
			if (index < inputRefs.length - 1) {
				inputRefs[index + 1].current?.focus();
			}
		} else if (event.key === 'Backspace' && index > 0) {
			inputRefs[index - 1].current?.focus();
		}
	};

	const handleKeyDown = (event: KeyboardEvent<HTMLInputElement>, index: number) => {
		if (event.key === 'Backspace' && index > 0 && !event.currentTarget.value) {
			inputRefs[index - 1].current?.focus();
		}
	};

	useEffect(() => {
		setCertCode(first + second + third + fourth);
	}, [first, second, third, fourth]);

	return (
		<CertCodeInputContainer>
			{inputRefs.map((ref, index) => (
				<input
					type="text"
					key={index}
					maxLength={1}
					onKeyUp={(e) => handleKeyUp(e, index)}
					onKeyDown={(e) => handleKeyDown(e, index)}
					ref={ref}
					onChange={(e) => setCodeFuncArr[index](e.target.value)}
				/>
			))}
		</CertCodeInputContainer>
	);
}

export default CertCodeInput;
