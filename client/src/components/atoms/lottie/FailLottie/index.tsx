import React from 'react';
import Lottie, { Options } from 'react-lottie';
import FailAnimation from './lottie.json';

function FailLottie() {
	const defaultOptions: Options = {
		loop: true,
		autoplay: true,
		animationData: FailAnimation,
	};

	return <Lottie options={defaultOptions} width={350} height={350} />;
}

export default FailLottie;
