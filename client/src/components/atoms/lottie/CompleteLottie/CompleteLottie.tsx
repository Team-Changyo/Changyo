import React from 'react';
import Lottie, { Options } from 'react-lottie';
import CompleteAnimation from './CompleteLottie.json';

function CompleteLottie() {
	const defaultOptions: Options = {
		loop: true,
		autoplay: true,
		animationData: CompleteAnimation,
	};

	return <Lottie options={defaultOptions} width={350} height={350} />;
}

export default CompleteLottie;
